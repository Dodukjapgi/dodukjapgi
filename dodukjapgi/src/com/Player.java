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
        final Card drawnCard = target.removeCardAt(index);
        addCard(drawnCard);
        return drawnCard;
    }
}
