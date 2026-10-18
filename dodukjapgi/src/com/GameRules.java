package com;

public interface GameRules {
    String getDescription();

    boolean isPair(Card first, Card second);

    boolean isGameOver(Player[] players);

    Player findLoser(Player[] players);
}
