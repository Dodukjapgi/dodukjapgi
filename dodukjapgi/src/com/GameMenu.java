package com;

import com.dto.MemberDTO;

import java.util.Scanner;

public class GameMenu {

    private Scanner sc = new Scanner(System.in);
    private MemberDTO member;
    private GameRules rules = new ThiefGameRules();

    public void registerMember() {
        // TODO: 회원 이름과 나이 입력받기
        // TODO: MemberDTO 객체를 만들어 member에 저장
    }

    public void showMainMenu() {
        // TODO: 반복문으로 메뉴 출력
        // TODO: 1. 게임 시작 / 2. 마이페이지 / 3. 규칙 / 0. 종료
        // TODO: 선택에 맞는 메서드 호출
    }

    public void showRules() {
        // TODO: rules.getDescription()을 호출해 규칙 출력
    }

    public void showMyPage() {
        // TODO: member의 이름, 나이, 점수 출력
    }

    public Player[] createPlayers() {
        // TODO: 참가 인원수 입력 및 범위 검사
        // TODO: 인원수만큼 Player 배열 생성
        // TODO: 첫 참가자의 이름은 member에서 가져오기
        // TODO: 나머지 참가자의 이름 입력받기
        // TODO: 각 참가자의 캐릭터 선택받기
        // TODO: 선택에 따라 Player1 또는 Player2 객체 저장
        // TODO: 완성한 배열 반환
        return null;
    }

    public void startGame() {
        // TODO: createPlayers()로 참가자 준비
        // TODO: Game 객체 생성 후 play() 호출
        // TODO: 반환받은 패배자가 로그인 회원의 플레이어인지 확인
        // TODO: 로그인 회원이 이겼다면 member에 500점 추가
        // TODO: 한 판 더 선택 시 새 Game 객체로 다시 진행
    }

    public boolean askReplay() {
        // TODO: 한 판 더 할지 입력받아 boolean 반환
        return false;
    }
}
