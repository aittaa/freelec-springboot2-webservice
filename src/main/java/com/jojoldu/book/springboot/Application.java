
package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
//@SpringBootApplication이 있는 위치부터 설정 읽어 내려가기에 당 클래스는 항상 프로젝트의 최상단 위치
public class Application { // 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 was 톰캣 실행
    }
}