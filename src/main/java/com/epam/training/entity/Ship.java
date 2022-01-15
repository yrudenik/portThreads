package com.epam.training.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Ship implements Runnable {

    @JsonProperty
    private int shipId;
    @JsonProperty
    private boolean isLoaded;

    public Ship() {
    }

    public Ship(int shipId, boolean isLoaded) {
        this.shipId = shipId;
        this.isLoaded = isLoaded;
    }

    public void run() {
        Port port = Port.getInstance();
            port.loadUnload(this);
    }

    public int getShipId() {
        return shipId;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Ship)) {
            return false;
        }
        Ship ship = (Ship) object;
        return isLoaded == ship.isLoaded && Objects.equals(shipId, ship.shipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipId, isLoaded);
    }

    @Override
    public String toString() {
        return String.format("Ship{ id = %s, isLoaded = %s }", shipId, isLoaded);
    }
}
