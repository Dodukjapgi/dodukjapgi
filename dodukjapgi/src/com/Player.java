package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    protected Player(String name) {
        this.name = name;
    }

    public abstract String getCharacterName();

    public abstract String getTurnMessage();

    public String getName() {
        return name;
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

//    public boolean hasJoker() {
//        return hand.stream().anyMatch(Card::isJoker);
//    }

    public Card getCardAt(int index) {
        checkIndex(index);
        return hand.get(index);
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card removeCardAt(int index) {
        checkIndex(index);
        return hand.remove(index);
    }

    public Card drawFrom(Player target, int index) {
        Card drawn = target.removeCardAt(index);
        addCard(drawn);
        return drawn;
    }

    public void shuffleHand() {
        Collections.shuffle(hand);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IllegalArgumentException("잘못된 카드 번호: " + index);
        }
    }
}