package com;

public class ThiefGameRules implements GameRules {
    @Override
    public String getDescription() {
        // TODO: 메뉴에서 보여 줄 도둑잡기 규칙 설명 반환
        return "도둑잡기 게임 규칙\n"
                + "1. 일반 카드 52장과 조커 1장을 사용합니다.\n"
                + "2. 같은 숫자의 카드 두 장은 짝으로 제거합니다.\n"
                + "3. 차례마다 상대방의 카드 한 장을 가져옵니다.\n"
                + "4. 마지막에 조커를 가진 플레이어가 패배합니다.";
    }

    @Override
    public boolean isPair(Card first, Card second) {
        // TODO: 하나라도 조커라면 false 반환
        // TODO: 일반 카드의 숫자가 같으면 true 반환
        if (first.isJoker() || second.isJoker()) {
            return false;
        }
        return first.getNumber() == second.getNumber();
    }

    @Override
    public boolean isGameOver(Player[] players) {

        // 전제: 일반 카드 52장과 조커 1장으로 시작
        int remainingPlayers = 0;
        int remainingCards = 0;

        for (Player player : players) {

            if (player.getHandSize() > 0) {
                remainingPlayers++;
            }

            remainingCards += player.getHandSize();
        }
        return remainingPlayers == 1 && remainingCards == 1;
    }

    @Override
    public Player findLoser(Player[] players) {
        // TODO: 게임 종료 여부 확인
        // TODO: 패가 남은 플레이어 반환
        if (!isGameOver(players)) {
            return null;
        }

        for (Player player : players) {

            if (player.getHandSize() > 0) {
                return player;
            }
        }
        return null;
    }
}
