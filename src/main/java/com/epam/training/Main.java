package com.epam.training;

import com.epam.training.entity.Ship;
import com.epam.training.entity.ShipsWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        String filePath = "src/main/resources/ships.json";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ShipsWrapper shipsWrapper = objectMapper.readValue(new File(filePath), ShipsWrapper.class);
            List<Ship> ships = shipsWrapper.getShips();

            ExecutorService executorService = Executors.newFixedThreadPool(ships.size());
            ships.forEach(executorService::submit);
            //ships.forEach(ship -> executorService.submit(new Thread(ship)));
            executorService.shutdown();
        } catch (IOException e) {
            LOGGER.warn("IOException caught", e);
        }
    }
}
