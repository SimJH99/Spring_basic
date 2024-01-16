package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
public class MemberController {
    //service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
    //스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
    //개발자가 제어하는 것이 아니라 스프링이 제어한다고 해서
    //제어의 역전(Inversion of Control) -> IOC컨테이너가 스프링빈을 관리(빈을 생성, 의존성 주입)
//    @Autowired  //의존성 주입(DI) 방법1 => 필드주입방식
//    private MemeberService memberService;

//    의존성 주입방법2 => 생성자 주입 방식, 가장많이 사용
//    장점 : final을 통해 상수로 사용가능, 다형성 구현 가능, 순환잠조방지
//    생성자가 1개밖에 없을 때에는 Autowired 생략가능
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

////    의존성주입방법3. @RequiredArgsConstructor를 이용한 방식
////    @RequiredArgsConstructor : @NonNul어노테이션이 붙어있는 필드 또는 fianl이 초기화 되지 않은 final필드를 대상으로 생성자 생성
//    private final MemeberService memberService;


    @GetMapping("/")
    public String home(){
        return "member/header";
    }

    @GetMapping("members/create")
    public String memberCreateScreen(){
        return "./member/member-create-screen";
    }

    @PostMapping("members/create")
    public String memberCreate(MemberRequestDto memberRequestDto) {
        memberService.memberCreate(memberRequestDto);

        //url 리다리렉트
        return "redirect:/members";
    }

    @GetMapping("members")
    public String members(Model model){
        model.addAttribute("memberList", memberService.members());
        return "./member/memberList";
    }

    @GetMapping("/member/find")
    public String memberFind(Model model, @RequestParam(value = "id") int id) {
        try{
            MemberResponseDto memberResponseDto = memberService.findById(id);
            model.addAttribute("member", memberResponseDto);
            return "/member/member-detail";
        } catch (NoSuchElementException e){
            return "/member/404-error-page";
        }
    }
}
