package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public abstract int chooseCardIndex(Player target);

    public String getName() {
        return name;
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card removeCardAt(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IllegalArgumentException("잘못된 카드 번호: " + index);
        }
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

    public String showHand() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            stringBuilder.append(String.format("(%d)%s ", i + 1, hand.get(i)));
        }
        return stringBuilder.toString().trim();
    }

    public String showHiddenHand() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hand.size(); i++) {
            stringBuilder.append(String.format("(%d)[?] ", i + 1));
        }
        return stringBuilder.toString().trim();
    }
}