package com.epam.training.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ShipsWrapper {

    @JsonProperty
    private List<Ship> ships;

    public List<Ship> getShips() {
        return ships;
    }
}
