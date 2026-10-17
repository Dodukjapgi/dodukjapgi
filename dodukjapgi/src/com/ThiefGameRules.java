package com;
public class ThiefGameRules implements GameRules {

    @Override
    public boolean isPair(Card first, Card second) {
        // TODO: 둘 중 하나라도 조커라면 false 반환
        // TODO: 일반 카드 두 장의 숫자가 같으면 true 반환

        return false;
    }

    @Override
    public boolean isGameOver(Player[] players) {
        // TODO: getHandSize()로 각 플레이어의 카드 수 확인
        // TODO: 패가 남은 사람이 한 명이고 카드가 한 장이면 true 반환
        // 전제: 조커 한 장과 짝이 맞는 일반 카드로 덱을 구성

        return false;
    }

    @Override
    public Player findLoser(Player[] players) {
        // TODO: 게임이 끝났는지 먼저 확인
        // TODO: isEmpty()가 false인 플레이어를 찾아 반환

        return null;
    }

    @Override
    public String getDescription() {
        // TODO: 메뉴에서 보여 줄 규칙 설명 반환
        return null;
    }
}

