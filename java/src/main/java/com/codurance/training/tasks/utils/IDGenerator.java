package com.codurance.training.tasks.utils;

public class IDGenerator {
    private static long lastId = 0;

    public static synchronized long nextId() {
        return ++lastId;
    }
}
