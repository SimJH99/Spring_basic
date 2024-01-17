package com.encore.basic.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberUpdateDto {
    private int id;
    private String name;
    private String password;
    private LocalDateTime update_time;
}
