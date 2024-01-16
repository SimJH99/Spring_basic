package com.encore.basic.service;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.domain.Member;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberService {

//    싱글톤의 다형성
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MybatisMemberRepository mybatisMemberRepository){
        this.memberRepository = mybatisMemberRepository;
    }


    public List<MemberResponseDto> members(){
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();

        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(member.getId());
            memberResponseDto.setName(member.getName());
            memberResponseDto.setEmail(member.getEmail());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    public void memberCreate(MemberRequestDto memberRequestDto){
        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
        memberRepository.save(member);
    }

    public MemberResponseDto findById(int id) throws NoSuchElementException{
       Member member =  memberRepository.findById(id).orElseThrow(NoSuchElementException::new);
       MemberResponseDto memberResponseDto = new MemberResponseDto();
       memberResponseDto.setId(member.getId());
       memberResponseDto.setName(member.getName());
       memberResponseDto.setEmail(member.getEmail());
       memberResponseDto.setPassword(member.getPassword());
       memberResponseDto.setCreate_time(member.getCreate_time());

       return memberResponseDto;
    }
}
