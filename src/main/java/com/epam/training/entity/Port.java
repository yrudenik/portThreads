package com.epam.training.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int NUMBER_OF_DOCKS = 4;

    List<Dock> docks = new ArrayList<>();
    Semaphore semaphore = new Semaphore(NUMBER_OF_DOCKS);
    private static final Lock lock = new ReentrantLock();
    private static Port instance;

    public static Port getInstance() {
        Port localInstance = instance;
        if (localInstance == null) {
            lock.lock();
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new Port();
            }
            lock.unlock();
        }
        return localInstance;
    }

    void loadUnload(Ship ship) {
        try {
            semaphore.acquire();
            lock.lock();
            LOGGER.info(String.format("%s moored %s", ship, this));
            Dock dock = docks.get(0);
            dock.serve(ship);
            //System.out.println("dock.getShipsServed() = " + dock.getShipsServed());
            LOGGER.info(String.format("%s served %s", ship, this));
        } catch (InterruptedException e) {
            LOGGER.warn("InterruptedException caught", e);
        } finally {
            semaphore.release();
            lock.unlock();
        }
    }
}

