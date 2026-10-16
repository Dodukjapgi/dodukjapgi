package com;

public class Player2 extends Player {
    public Player2(String name) {
        super(name);
    }

    @Override
    public String getCharacterName() {
        return "플레이어 2";
    }

    @Override
    public void introduce() {
        System.out.println("플레이어 2입니다.");
    }
}