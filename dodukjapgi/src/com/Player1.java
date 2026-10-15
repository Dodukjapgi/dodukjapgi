package com;

public class Player1 extends Player {
    public Player1(String name) {
        super(name);
    }

    @Override
    public String getCharacterName() {
        return "플레이어 1";
    }

    @Override
    public void introduce() {
        System.out.println("플레이어 1입니다.");
    }
}