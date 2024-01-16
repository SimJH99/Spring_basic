package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
// 모든 매개변수를 넣은 생성자 어노테이션
@AllArgsConstructor
public class Member {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime create_time;
}