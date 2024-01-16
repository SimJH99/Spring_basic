package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
    private String password;
}
