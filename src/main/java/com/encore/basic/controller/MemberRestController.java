package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

import static com.encore.basic.controller.ResponseEntityController.*;

@Api(tags = "회원관리서비스")
@RestController
@RequestMapping("/rest")
public class MemberRestController {
    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home() {
        return "member/header";
    }

    @PostMapping("members/create")
    public String memberCreate(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.memberCreate(memberRequestDto);
        return "ok";
    }

    @GetMapping("members")
    public List<MemberResponseDto> members() {
        return memberService.members();
    }

    @GetMapping("/members/find/{id}")
    public ResponseEntity<Map<String, Object>> memberFind(@PathVariable int id) {
        MemberResponseDto memberResponseDto = null;
        try{
            memberResponseDto = memberService.findById(id);
            return responseMessage(HttpStatus.OK, memberResponseDto);
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return errorResponseMessage(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @DeleteMapping("/members/delete/{id}")
    public String memberDelete(@PathVariable int id){
        memberService.MemberDelete(id);
        return "ok";
    }

    @PatchMapping("members/update")
    public MemberResponseDto memberUpdate(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.memberUpdate(memberRequestDto);
        return memberService.findById(memberRequestDto.getId());
    }

    
}
