package com;

public class Player1 extends Player {
    public Player1(String name) {
        super(name);
    }

    @Override
    public String getCharacterName() {
        return "여우";
    }

    @Override
    public String getTurnMessage() {
        return "후후, 어떤 카드가 조커일까?";
    }
}