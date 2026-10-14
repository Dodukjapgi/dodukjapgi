package com;

public class Player {
    private final String name;
    private Card[] cards;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
