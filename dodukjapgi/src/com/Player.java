package com;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public void clearHand() {
        hand.clear();
    }

    public int getHandSize() {
        return hand.size();
    }

    public void removeCard(Card card) {
        hand.remove(card);
    }

    public boolean hasCard(Card card) {
        return hand.contains(card);
    }

    public Card getCard(int index) {
        return hand.get(index);
    }

    public void removeCard(int index) {
        hand.remove(index);
    }

    public boolean hasCard(int index) {
        return index >= 0 && index < hand.size();
    }
}
