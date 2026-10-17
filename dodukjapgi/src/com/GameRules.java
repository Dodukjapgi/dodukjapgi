package com;

public interface GameRules {

    // 두 카드가 짝인지 판단
    boolean isPair(Card first, Card second);

    // 게임이 끝났는지 판단
    boolean isGameOver(Player[] players);

    // 패배한 플레이어 반환
    Player findLoser(Player[] players);

    // 규칙 설명 반환
    String getDescription();
}
