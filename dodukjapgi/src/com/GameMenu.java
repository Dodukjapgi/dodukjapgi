package com;

import com.dto.MemberDTO;

import java.util.Scanner;

public class GameMenu {

    private Scanner sc = new Scanner(System.in);
    private MemberDTO member;
    private GameRules rules = new ThiefGameRules();
    private Player[] players;

    public void registerMember() {

        this.member = new MemberDTO();

        System.out.print("당신의 이름을 입력하세요: ");
        String name = sc.next();

        System.out.print("당신의 나이를 입력하세요: ");
        int age = sc.nextInt();

        member.setName(name);
        member.setAge(age);
    }

    public void showMainMenu() {

        while (true) {

            System.out.println("========== 메인 메뉴 ==========");
            System.out.println("1. 게임시작");
            System.out.println("2. 마이페이지");
            System.out.println("3. 규칙");
            System.out.println("0. 종료");
            System.out.print("번호를 선택하세요: ");

            int num = sc.nextInt();

            if (num == 1) {

                System.out.println("게임을 시작하겠습니다!!");
                startGame();

            } else if (num == 2) {

                System.out.println("========== 마이페이지 ==========");
                showMyPage();

            } else if (num == 3) {

                System.out.println("========== 게임 규칙 확인 ==========");
                System.out.println("도둑잡기 게임의 규칙을 안내해 드립니다.");
                showRules();

            } else if (num == 0) {

                System.out.println("게임을 종료하겠습니다...");
                break;

            } else {

                System.out.println("번호를 잘못입력하셨습니다..");
                System.out.println("메인 메뉴로 돌아갑니다.");
            }
        }
    }

    public void showRules() {
        System.out.println(rules.getDescription());
    }

    public void showMyPage() {

        System.out.println("이름: " + member.getName());
        System.out.println("나이: " + member.getAge());
        System.out.println("회원 누적 점수: " + member.getPoint() + "점");

        if (players == null) {
            System.out.println("아직 게임 기록이 없습니다.");
            return;
        }

        System.out.println("========== 참가자 점수 ==========");

        for (Player player : players) {
            System.out.println(
                    player.getName() + " : "
                            + player.getPoint() + "점"
            );
        }
    }

    public Player[] createPlayers() {

        System.out.print("참가 인원수를 입력하세요 :");
        int playerNum = sc.nextInt();

        while (playerNum < 2 || playerNum > 3) {

            System.out.println("2 ~ 3명만 참가할 수 있습니다");
            System.out.print("참가 인원수를 입력하세요 :");
            playerNum = sc.nextInt();
        }

        Player[] player = new Player[playerNum];

        for (int i = 0; i < player.length; i++) {

            String name;

            if (i == 0) {

                System.out.print("1번 참가자: ");
                name = sc.next();
                player[i] = new Player1(name);

            } else if (i == 1) {

                System.out.print("2번 참가자: ");
                name = sc.next();
                player[i] = new Player2(name);

            } else {

                System.out.print("3번 참가자: ");
                name = sc.next();
                player[i] = new Player3(name);
            }
        }

        return player;
    }

    public void startGame() {

        if (players == null) {
            players = createPlayers();
        }

        do {
            for (Player player : players) {
                player.clearHand();
            }

            // nextInt(), next() 이후 남은 개행 문자 제거
            sc.nextLine();

            Game game = new Game(players, rules, sc);
            Player loser = game.play();

            for (Player player : players) {

                if (loser != null && player != loser) {
                    player.addPoint(500);

                    if (player.getName().equals(member.getName())) {
                        member.addPoint(500);
                    }
                }

                player.clearHand();
            }

        } while (askReplay());

        System.out.println("게임을 종료합니다.");
    }

    public boolean askReplay() {

        System.out.print(
                "한 판 더 하시겠습니까? (1. 예 / 2. 아니오) : "
        );

        int num = sc.nextInt();

        if (num == 1) {
            return true;

        } else if (num == 2) {
            return false;

        } else {

            while (num != 1 && num != 2) {

                System.out.println("잘못된 번호입니다.");
                System.out.print(
                        "다시 입력해 주세요(1. 예 / 2. 아니오) : "
                );

                num = sc.nextInt();
            }

            return num == 1;
        }
    }
}