package com;

import com.dto.MemberDTO;

import java.util.Scanner;

public class GameMenu {

    private Scanner sc = new Scanner(System.in);
    private MemberDTO member;
    private GameRules rules = new ThiefGameRules();
    private Player[] players;

    public void registerMember() {
        // TODO: 회원 이름과 나이 입력받기
        // TODO: MemberDTO 객체를 만들어 member에 저장
        this.member = new MemberDTO();
        System.out.print("당신의 이름은 입력하세요: ");
        String name = sc.next();
        System.out.println("당신의 나이를 입력하세요: ");
        int age = sc.nextInt();

        member.setName(name);
        member.setAge(age);
    }

    public void showMainMenu() {
        // TODO: 반복문으로 메뉴 출력
        // TODO: 1. 게임 시작 / 2. 마이페이지 / 3. 규칙 / 0. 종료
        // TODO: 선택에 맞는 메서드 호출
            while(true) {
                System.out.println("========== 메인 메뉴 ==========");
                System.out.println("1. 게임시작");
                System.out.println("2. 마이페이지");
                System.out.println("3. 규칙");
                System.out.println("0. 종료");
                System.out.println("번호를 선택하세요: ");
                int num = sc.nextInt();
                if(num == 1) {
                    System.out.println("게임을 시작하겠습니다!!");
                    startGame();
                } else if(num == 2) {
                    System.out.println("========== 마이페이지 ==========");
                    showMyPage();
                } else if(num == 3) {
                    System.out.println("========== 게임 규칙 확인 ==========");
                    System.out.println("도둑잡기 게임의 규칙을 안내해 드립니다.");
                    showRules();
                } else if(num == 0) {
                    System.out.println("게임을 종료하겠습니다...");
                    break;
                } else {
                    System.out.println("번호를 잘못입력하셨습니다..");
                    System.out.println("메인 메뉴로 돌아갑니다.");
                }
            }

    }

    public void showRules() {
        // TODO: rules.getDescription()을 호출해 규칙 출력
        System.out.println(rules.getDescription());
    }

    public void showMyPage() {
        // TODO: member의 이름, 나이, 점수 출력
        System.out.println("이름: " + member.getName());
        System.out.println("나이: " + member.getAge());

        if (players == null) {
            System.out.println("아직 게임 기록이 없습니다.");
            return;
        }

        System.out.println("========== 참가자 점수 ==========");

        for (Player player : players) {
            System.out.println(player.getName() + " : " + player.getPoint() + "점");
        }
    }

    public Player[] createPlayers() {
        // TODO: 참가 인원수 입력 및 범위 검사
        // TODO: 인원수만큼 Player 배열 생성
        // TODO: 참가자의 이름 입력받기
        // TODO: 선택에 따라 Player1, Player2,  Player3 객체 저장
        // TODO: 완성한 배열 반환
        System.out.print("참가 인원수를 입력하세요 :");
        int playerNum = sc.nextInt();
        while (playerNum < 2 || playerNum > 3) {
            System.out.println("2 ~ 3명만 참가할 수 있습니다");
            System.out.print("참가 인원수를 입력하세요 :");
            playerNum = sc.nextInt();
        }
        Player[] player = new Player[playerNum];
        for(int i = 0; i < player.length; i++) {
            String name;
            if(i == 0) {
                System.out.print("1번 참가자: ");
                name = sc.next();
                player[i] = new Player1(name);
            } else if(i == 1) {
                System.out.print("2번 참가자: ");
                name = sc.next();
                player[i] = new Player2(name);
            } else {
                System.out.println("3번 참가자: ");
                name = sc.next();
                player[i] = new Player3(name);
            }

        }
        return player;
    }

    public void startGame() {
        // TODO: createPlayers()로 참가자 준비
        // TODO: Game 객체 생성 후 play() 호출
        // TODO: 반환받은 패배자가 로그인 회원의 플레이어인지 확인
        // TODO: 로그인 회원이 이겼다면 member에 500점 추가
        // TODO: 한 판 더 선택 시 새 Game 객체로 다시 진행

        if(players == null) {
            players = createPlayers();
        }
        do {
            Game game = new Game(players, rules, sc);
            Player loser = game.play();

            for (Player player : players) {
                if (player != loser) {
                    player.addPoint(500);
                }
                player.clearHand();
            }

        }
        while(askReplay() == true);
        System.out.println("게임을 종료합니다.");

    }

    public boolean askReplay() {
        // TODO: 한 판 더 할지 입력받아 boolean 반환
        System.out.print("한 판 더 하시겠습니까? (1. 예 / 2. 아니오) : ");
        int num = sc.nextInt();

        if (num == 1) {
            return true;
        } else if (num == 2) {
            return false;
        } else {
            while(num != 1 && num != 2) {
                System.out.println("잘못된 번호입니다.");
                System.out.println("다시 입력해 주세요(1. 예 / 2. 아니오) : ");
                num = sc.nextInt();
            }
            return num == 1;
        }
    }
}
