package com;

public class NormalCard extends Card {

    private int number;
    private String suit; // 문양

    public NormalCard(int number, String suit) {
        // TODO: 숫자와 문양 초기화
        this.number = number;
        this.suit = suit;

    }

    @Override
    public boolean isJoker() {
        // TODO: 일반 카드이므로 false 반환

        return false;
    }

    @Override
    public int getNumber() {
        return number;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        // TODO: 숫자와 문양을 문자열로 반환
        return "숫자: " +  number + " 모양: " + suit ;
    }
}
