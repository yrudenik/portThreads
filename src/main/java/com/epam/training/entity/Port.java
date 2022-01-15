package com.epam.training.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Port {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int NUMBER_OF_DOCKS = 4;

    Queue<Dock> docks = new LinkedList<>();
    Semaphore semaphore = new Semaphore(NUMBER_OF_DOCKS);
    private static final Lock lock = new ReentrantLock();
    private static Port instance;

    public static Port getInstance() {
        Port localInstance = instance;
        if (localInstance == null) {
            lock.lock();
            try {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Port();
                    instance.createDocks();
                }
            } finally {
                lock.unlock();
            }
        }
        return localInstance;
    }

    void loadUnload(Ship ship) {
        try {
            semaphore.acquire();
            lock.lock();
            LOGGER.info(String.format("%s moored %s", ship, this));
            Dock dock = docks.poll();
            if (dock != null) {
                dock.serve(ship);
            }
            docks.offer(dock);
            LOGGER.info(String.format("%s served %s", ship, this));
        } catch (InterruptedException e) {
            LOGGER.warn("InterruptedException caught", e);
        } finally {
            semaphore.release();
            lock.unlock();
        }
    }

    void createDocks(){
        IntStream.range(0, NUMBER_OF_DOCKS).mapToObj(dock -> new Dock()).forEach(dock -> docks.add(dock));
        }
}

