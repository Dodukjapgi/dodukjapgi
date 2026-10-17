package com;

public class Player3 extends Player {
    public Player3(String name) {
        super(name);
    }

    @Override
    public String getCharacterName() {
        return "플레이어 3";
    }

    @Override
    public void introduce() {
        System.out.println("플레이어 3입니다.");
    }
}