package com;

public class NormalCard extends Card {
    private final int number;
    private final String suit; // 문양

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

    @Override
    public String toString() {
        String cardNumber = switch (number) {
            case 1 -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(number);
        };
        return "숫자: " + cardNumber + " 모양: " + suit;
    }
}