package com.epam.training.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Queue;

public class ShipsWrapper {

    @JsonProperty
    private Queue<Ship> ships;

    public Queue<Ship> getShips() {
        return ships;
    }
}
