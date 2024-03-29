package com.encore.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
public class MemberRequestDto {
    private int id;
    private String name;
    private String email;
    private String password;
}
