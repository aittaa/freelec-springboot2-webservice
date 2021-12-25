package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // lombok : 선언된 final 필드가 포함된 생성자를 자동생성

public class HelloResponseDto {
    private final String name;
    private final int amount;
}