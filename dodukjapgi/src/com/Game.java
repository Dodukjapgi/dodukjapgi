package com;

import java.util.*;

public class Game {

    private Player[] players;
    private Card[] deck;
    private GameRules rules;
    private int currentPlayerIndex;
    private Scanner sc;

    public Game(Player[] players, GameRules rules, Scanner sc) {
        // TODO: 전달받은 참가자, 규칙, Scanner 저장
        this.players = players;
        this.rules = rules;
        this.sc = sc;
        // TODO: 첫 번째 참가자부터 시작하도록 현재 위치 초기화
        this.currentPlayerIndex = 0;
    }

    public Player play() {
        // TODO: 덱 생성 → 섞기 → 나누기
        createDeck();
        shuffleDeck();
        dealCards();


        // TODO: 모든 참가자의 초기 패에서 짝 제거
        for (Player player : players) {
            removePairs(player);
        }
        // TODO: 한 사람씩 자기 패 확인
        for (Player player : players) {
            showHand(player);
        }
        // TODO: 게임이 끝날 때까지 playTurn()과 nextTurn() 반복
        while (!rules.isGameOver(players)) {

            playTurn();

            // 게임이 종료되지 않았을 때만 다음 턴
            if (!rules.isGameOver(players)) {
                nextTurn();
            }
        }
        // TODO: 패가 없는 참가자는 차례 건너뛰기
        if (players[currentPlayerIndex].getHandSize() == 0) {
            nextTurn();
        }
        // TODO: 게임 종료 후 패배자 찾기
        Player loser = rules.findLoser(players);
        // TODO: 결과 출력 후 패배자 반환
        showResult(loser);
        return loser;
    }

    private void createDeck() {
        // TODO: 일반 카드 52장 + 조커 1장을 담을 배열 생성
        deck = new Card[53];
        // TODO: 문양 4종류와 숫자 1~13을 조합해 일반 카드 생성
        String[] suits = {"♠️", "♥️","♣️","♦️"};
        int index = 0;

        for(String suit : suits) {
            for(int num =1; num <14; num++) {
                deck[index] =new NormalCard(num, suit);
                index++;
            }
        }

        // TODO: 마지막 자리에 조커 저장
        deck[52] = new JokerCard();
    }

    private void shuffleDeck() {
        // TODO: 덱의 카드 위치를 무작위로 교환
        Collections.shuffle(Arrays.asList(deck));
    }

    private void dealCards() {
        // TODO: 참가자들에게 한 장씩 돌아가며 분배
        // TODO: 각 참가자의 addCard() 사용

        for(int i =0; i < deck.length; i++) {

            int playerIndex = i % players.length;

            players[playerIndex].addCard(deck[i]);
        }
    }

    private void removePairs(Player player) {
        // TODO: getCardAt()으로 패 안의 카드 두 장 비교
        // TODO: rules.isPair()가 true이면 두 장 제거
        // TODO: 큰 인덱스부터 제거해야 위치가 덜 꼬임
        // TODO: 제거 후 바뀐 카드 위치를 고려해 다음 비교 진행
        boolean foundPair = true;

        while (foundPair) {

            foundPair = false;

            for (int i = 0; i < player.getHandSize(); i++) {

                for (int j = i + 1; j < player.getHandSize(); j++) {

                    Card first = player.getCardAt(i);
                    Card second = player.getCardAt(j);

                    if (rules.isPair(first, second)) {

                        // 큰 인덱스부터 제거
                        player.removeCardAt(j);
                        player.removeCardAt(i);

                        foundPair = true;
                        break;
                    }
                }

                if (foundPair) {
                    break;
                }
            }
        }
    }

    private void showHand(Player player) {
        // TODO: getHandSize()만큼 반복
        // TODO: getCardAt()으로 자기 카드 출력
        // TODO: 다른 참가자가 볼 수 없도록 확인 순서 안내
        System.out.println();
        System.out.println("[" + player.getName() + "의 카드]");

        for (int i = 0; i < player.getHandSize(); i++) {

            System.out.println(
                    (i + 1) + ". " + player.getCardAt(i)
            );
        }


    }
    private void playTurn() {
        // TODO: 현재 플레이어와 카드를 뽑을 상대 찾기
        // TODO: 상대의 남은 카드 개수 출력
        // TODO: 선택 번호 입력받기
        // TODO: drawFrom()으로 상대 카드 가져오기
        // TODO: 현재 플레이어의 짝 제거
        // TODO: 현재 플레이어의 남은 패 섞기
        Player current = players[currentPlayerIndex];
        Player target = findTarget();

        if (target == null) {
            return;
        }

        System.out.println();
        System.out.println("현재 차례: " + current.getName());
        System.out.println("카드를 뽑을 상대: " + target.getName());

        int cardIndex = inputCardIndex(target);

        // 상대방 카드 가져오기
        Card drawnCard = current.drawFrom(target, cardIndex);

        System.out.println(
                current.getName() + "이(가) 카드를 뽑았습니다."
        );

        // 같은 숫자 카드 제거
        removePairs(current);

        // 자신의 카드 순서 섞기
        current.shuffleHand();

        showHand(current);
    }

    private Player findTarget() {
        // TODO: 현재 플레이어 다음 위치부터 탐색
        // TODO: 배열 끝에 도달하면 처음으로 돌아가기
        // TODO: 자신을 제외하고 패가 남은 첫 번째 상대 반환
        int index = currentPlayerIndex;

        for (int i = 0; i < players.length - 1; i++) {

            index = (index + 1) % players.length;

            if (players[index].getHandSize() > 0) {
                return players[index];
            }
        }
        return null;
    }

    private int inputCardIndex(Player target) {
        // TODO: 1부터 상대의 패 개수 사이의 번호 입력받기
        // TODO: 잘못된 입력이면 다시 입력받기
        // TODO: 선택 번호에서 1을 빼서 인덱스로 반환
        int number = 0;

        while (number < 1 || number > target.getHandSize()) {

            System.out.print("카드 번호 선택 (1~"
                    + target.getHandSize() + "): ");

            try {
                number = Integer.parseInt(sc.nextLine());

                if (number < 1 || number > target.getHandSize()) {
                    System.out.println("잘못된 번호입니다.");
                }

            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
            }
        }

        return number - 1;
    }

    private void nextTurn() {
        // TODO: 다음 참가자 위치로 이동
        // TODO: 배열 끝이면 처음으로 돌아가기
        // TODO: 패가 없는 참가자는 건너뛰기
        // TODO: 게임 종료 후에는 이 메서드를 호출하지 않기
        for (int i = 0; i < players.length; i++) {

            currentPlayerIndex =
                    (currentPlayerIndex + 1) % players.length;

            if (players[currentPlayerIndex].getHandSize() > 0) {
                return;
            }
        }

    }

    private void showResult(Player loser) {
        // TODO: 패배자 이름 출력
        // TODO: 나머지 참가자들의 승리 출력
        System.out.println("===== 게임 종료 =====");

        if (loser == null) {
            System.out.println("패배자를 확인할 수 없습니다.");
            return;
        }

        System.out.println("패배자 : " + loser.getName());

        for (Player player : players) {

            if (player != loser) {
                System.out.println("승리자 : " + player.getName());
            }
        }
    }
}
