package com;

public class JokerCard extends Card {

    public JokerCard() {
    }

    @Override
    public boolean isJoker() {
        // TODO: 조커이므로 true 반환
        return false;
    }

    @Override
    public int getNumber() {
        // 조커는 숫자로 짝을 비교하지 않음
        return 0;
    }

    @Override
    public String toString() {
        // TODO: 조커를 나타내는 문자열 반환
        return null;
    }
}