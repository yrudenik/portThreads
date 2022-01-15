package com.epam.training.util;

public class IdGenerator {
    private static int id;

    public IdGenerator() {
    }

    public static int generateId(){
        return ++id;
    }
}
