package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) // ①
@WebMvcTest(controllers = HelloController.class) // ②
public class HelloControllerTest {
    @Autowired // ③
    private MockMvc mvc; // ④

    @Test
    public void helloCheck() throws Exception {
        String hello = "hello";
        mvc.perform(get("/hello")) // ⑤
                .andExpect(status().isOk()) // ⑥
                .andExpect(content().string(hello)); // ⑦
    }

    @Test
    public void helloDtoCheck() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name) // ⑧
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // ⑨
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}

// ① : RunWith는 JUnit에 내장된 실행자 외 다른 실행자 실행 // 스프링 부트 테스트와 JUnit 사이의 연결자 역할
// ② : WebMvcTest는 Web(Spring MVC)에 집중할 수 있는 어노케이션 // @컨트롤러, @컨트롤러Advice 사용가능
// ③ : 스프링이 관리하는 빈을 주입받는다.
// ④ : 웹 API 테스트할 때 사용, 스프링 MVC의 시작점
// ⑤ : MockMvc*를 통해 /hello 주소로 HTTP GET 요청 // 체이닝 지원 -> 여러 검증 기능 이어서 선언 가능
// ⑥ : HTTP Header Status 200인지 아닌지 검증
// ⑦ : 응답 본문 내용 검증
// ⑧ : API 테스트할 때 사용될 요청 파라미터를 설정 // String만 허용 // 숫자, 날짜 데이터는 String으로 변경해야 가능
// ⑨ : json 응답값을 필드별로 검증할 수 있는 메소드 // $를 기준으로 필드명 명시
//*
//Mockmvc : 실제 객체와 비슷하지만 테스트에 필요한 기능만 가지는 가짜 객체를 만들어
//  애플리케이션 서버에 배포하지 않고도 스프링 MVC 동작을 재현할 수 있는 클래스를 의미

// String.valueOf() : Object의 값을 String으로 변환 // 파라미터가 null이면 문자열 "null"을 만들어서 반환.