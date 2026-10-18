package com;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game {

    private final Player[] players;
    private final GameRules rules;
    private final Scanner scanner;
    private Card[] deck;
    private int currentPlayerIndex;

    public Game(Player[] players, GameRules rules, Scanner scanner) {
        this.players = players;
        this.rules = rules;
        this.scanner = scanner;
        this.currentPlayerIndex = 0;
    }

    public Player play() {
        // 덱 생성 → 섞기 → 나누기
        createDeck();
        shuffleDeck();
        dealCards();
        // 모든 참가자의 초기 패에서 짝 제거
        for (Player player : players) {
            removePairs(player);
        }
        // 게임이 끝날 때까지 턴 반복
        while (rules.isGameOver(players)) {
            // 카드가 없는 참가자는 건너뛰기
            if (players[currentPlayerIndex].isEmpty()) {
                nextTurn();
                continue;
            }
            Player current = players[currentPlayerIndex];
            System.out.println();
            System.out.println("===== " + current.getName() + " 차례 =====");
            // 본인 차례에만 카드 공개
            waitForEnter("본인 차례입니다. 엔터를 눌러 카드를 확인하세요.");
            showHand(current);
            // 카드 뽑기 진행
            playTurn();
            // 다음 사람에게 넘기기 전 확인
            waitForEnter("확인이 끝났으면 엔터를 눌러 다음 차례로 넘기세요.");
            clearScreen();
            if (rules.isGameOver(players)) {
                nextTurn();
            }
        }

        // 패배자 확인 및 결과 출력
        Player loser = rules.findLoser(players);
        showResult(loser);
        return loser;
    }

    private void createDeck() {
        // 일반 카드 52장 + 조커 1장
        deck = new Card[53];
        String[] suits = {"♠️", "♥️", "♣️", "♦️"};
        int index = 0;
        for (String suit : suits) {
            for (int num = 1; num < 14; num++) {
                deck[index] = new NormalCard(num, suit);
                index++;
            }
        }
        deck[52] = new JokerCard();
    }

    private void shuffleDeck() {
        Collections.shuffle(Arrays.asList(deck));
    }

    private void dealCards() {
        // 참가자들에게 카드를 한 장씩 분배
        for (int i = 0; i < deck.length; i++) {
            int playerIndex = i % players.length;
            players[playerIndex].addCard(deck[i]);
        }
    }

    private void removePairs(Player player) {
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
        System.out.println();
        System.out.println("[" + player.getName() + "의 카드]");
        for (int i = 0; i < player.getHandSize(); i++) {
            System.out.println(
                    (i + 1) + ". " + player.getCardAt(i)
            );
        }
    }

    private void playTurn() {
        Player current = players[currentPlayerIndex];
        Player target = findTarget();
        if (target == null) {
            return;
        }
        System.out.println();
        System.out.println("현재 차례: " + current.getName());
        System.out.println("카드를 뽑을 상대: " + target.getName());
        System.out.println(
                target.getName() + "의 남은 카드: "
                        + target.getHandSize() + "장"
        );
        int cardIndex = inputCardIndex(target);
        // 카드 뽑기 전 장수 확인
        System.out.println("뽑기 전: " + current.getHandSize());
        // 상대방 카드 가져오기
        current.drawFrom(target, cardIndex);
        // 카드 뽑은 직후 장수 확인
        System.out.println("뽑은 직후: " + current.getHandSize());
        System.out.println(
                current.getName() + "이(가) 카드를 뽑았습니다."
        );
        // 같은 숫자 카드 제거
        removePairs(current);
        // 짝 제거 후 장수 확인
        System.out.println("짝 제거 후: " + current.getHandSize());
        // 자신의 카드 순서 섞기
        current.shuffleHand();
        System.out.println("\n카드를 뽑은 후 나의 패:");
        showHand(current);
    }

    private Player findTarget() {
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
        int number = 0;
        while (number < 1 || number > target.getHandSize()) {
            System.out.print(
                    "카드 번호 선택 (1~"
                            + target.getHandSize() + "): "
            );
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
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
        for (int i = 0; i < players.length; i++) {
            currentPlayerIndex =
                    (currentPlayerIndex + 1) % players.length;
            if (players[currentPlayerIndex].getHandSize() > 0) {
                return;
            }
        }
    }

    private void showResult(Player loser) {
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

    // 엔터를 누를 때까지 대기
    private void waitForEnter(String message) {
        System.out.println(message);
        scanner.nextLine();
    }

    // 이전 참가자의 카드가 바로 보이지 않도록 화면 구분
    private void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}