package com.hatsnake.test.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 모든 필드에 get 메소드를 생성
@Getter
// 선언된 모든 final 필드가 포함된 생성자를 생성해줍니다.
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
