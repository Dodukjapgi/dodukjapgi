package com.dto;

public class MemberDTO {

    private String name;
    private int age;
    private int point;

    public MemberDTO() {
    }

    public MemberDTO(String name, int age) {
        // TODO: 이름과 나이 초기화
         this.name = name;
         this.age = age;
        // TODO: 최초 점수는 0으로 설정
        this.point = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void addPoint(int point) {
        // TODO: 기존 점수에 전달받은 점수 더하기
        this.point += point;
    }
}

