package com.encore.basic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
//entity어노테이션을 통해 mariaDB의 테이블 및 컬럼을 자동생성
//class명은 테이블명, 변수명은 컬럼명
@Entity
@NoArgsConstructor
public class Member {
    @Setter
    @Id //pk설정
    // identity = auto-increment설정. auto = JPA구현체가 자동으로 적절한 키생성 전략 선택.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    String은 DB에 varchar로 변환
    @Setter
    private String name;
    @Column(nullable = false, length = 50)
    private String email;
    @Setter
    private String password;
    @Setter
    @Column(name = "created_time")   //name옵션을 통해 DB에 컬럼명 별도 지정 가능
    private LocalDateTime create_time;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        create_time = LocalDateTime.now();
    }

    public void updateMember(String name, String password) {
        this.name = name;
        this.password = password;
    }
}