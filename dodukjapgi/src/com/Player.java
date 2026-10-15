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

    // ===== 캐릭터마다 달라지는 부분 (Player1, Player2가 구현) =====

    /**
     * 캐릭터 이름 (캐릭터 선택 화면 등에서 사용)
     */
    public abstract String getCharacterName();

    /**
     * 자기 차례에 출력할 한마디
     */
    public abstract String getTurnMessage();

    // ===== 모든 캐릭터가 공통으로 쓰는 부분 =====

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

    /**
     * index번째 카드를 확인만 한다 (패에서 빼지 않음). Game의 짝 비교, 패 출력용
     */
    public Card getCardAt(int index) {
        checkIndex(index);
        return hand.get(index);
    }

    /**
     * 카드 한 장을 패에 추가 (분배, 뽑기 공용)
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * index번째 카드를 패에서 빼서 반환 (상대가 뽑아갈 때, 짝을 버릴 때)
     */
    public Card removeCardAt(int index) {
        checkIndex(index);
        return hand.remove(index);
    }

    /**
     * 상대의 패에서 index번째 카드를 뽑아 내 패에 넣고, 뽑은 카드를 반환
     */
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