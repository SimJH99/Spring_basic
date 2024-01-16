package com.encore.basic.service;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.domain.Member;
import com.encore.basic.repository.MemberRepository;
import com.encore.basic.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemoryMemberRepository memoryMemberRepository){
        this.memberRepository = memoryMemberRepository;
    }

    static int total_id;

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
        total_id++;
        LocalDateTime now = LocalDateTime.now();
        Member member = new Member(total_id, memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword(), now);
        memberRepository.save(member);
    }

    public MemberResponseDto findById(int id){
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
