package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


//ResponseEntity : restApi에서 응답 헤더값 + body
//1) 상태값만 주는 방법 / 2)new Responseentity<body, 상태값> / 3) 메서드 체이닝
@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {
    //    @ResponseStatus 어노테이션 방식
    @GetMapping("/responsestatus")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus() {
        return "OK";
    }

    @GetMapping("/responsestatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member member() {
        Member member = new Member("name", "email", "password");
        return member;
    }


    //    ResponseEntity객체를 직접생성한 방식
    @GetMapping("/custom1")
    public ResponseEntity<Member> custom1() {
        Member member = new Member("name", "email", "password");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    //    ResponseEntity<String>일 경우 text/html로 설정
    @GetMapping("/custom2")
    public ResponseEntity<String> custom2() {
        String html = "<h1>없는 아이디 입니다.</h1>";
        return new ResponseEntity<>(html, HttpStatus.NOT_FOUND);
    }

    //    메서드 체이닝 방식 : ResponseEntity에 클래스 메서드도 사용
    @GetMapping("chaing1")
    public ResponseEntity<Member> chaingMethod1() {
        Member member = new Member("name", "email", "password");
        return ResponseEntity.ok(member);
    }

    @GetMapping("chaing2")
    public ResponseEntity<Member> chaingMethod2() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("chaing3")
    public ResponseEntity<Member> chaingMethod3() {
        Member member = new Member("name", "email", "password");
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }


    //    map형태의 메시지 커스텀
    static public ResponseEntity<Map<String, Object>> errorResponseMessage(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        body.put("error message", message);
        return new ResponseEntity<>(body, status);
    }

    //    status 201, message : 객체
    static public ResponseEntity<Map<String, Object>> responseMessage(HttpStatus status, Object object) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        body.put("success data", object);
        return new ResponseEntity<>(body, status);
    }
}
