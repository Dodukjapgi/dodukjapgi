package com;

public class Player2 extends Player {
    public Player2(String name) {
        super(name);
    }

    @Override
    public String getCharacterName() {
        return "곰";
    }

    @Override
    public String getTurnMessage() {
        return "이번엔 절대 안 속아!";
    }
}