package com.epam.training.entity;

import com.epam.training.util.IdGenerator;

public class Dock {

    private final int dockId;
    private int shipsServed;

    public Dock() {
        dockId = IdGenerator.generateId();
    }

    public int getDockId() {
        return dockId;
    }

    public int getShipsServed() {
        return shipsServed;
    }

    void serve(Ship ship) {
        ship.setLoaded(!ship.isLoaded());
        shipsServed++;
    }
}
