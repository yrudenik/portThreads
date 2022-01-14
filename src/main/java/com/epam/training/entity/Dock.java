package com.epam.training.entity;

public class Dock {

    private int shipsServed = 0;

    public Dock() {
    }

    public Dock(int shipsServed) {
        this.shipsServed = shipsServed;
    }

    public int getShipsServed() {
        return shipsServed;
    }

    public void serve(Ship ship) {
        ship.setLoaded(!ship.isLoaded());
        shipsServed++;
        //System.out.println("shipsServed = " + shipsServed);
    }
}
