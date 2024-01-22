package com.encore.basic.service;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberUpdateDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
//service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> 스프링 빈으로 등록
//스프링 빈이란 스프링이 생성하고 관리하는 객체를 의미
//제어의 역전(Inversion of control) -> IOC컨테이너가 스프링 빈을 관리(빈을 생성, 의존성 주입)
@Transactional
public class MemberService {
    
    

    //    싱글톤의 다형성
    private final MemberRepository memberRepository;
    // RequiredArgConstructor : 변수가 final이 붙어있는 경우에 해당 변수를 대상으로 생성자를 만듬
    // 이를 생성자 주입이라고 한다.
    
    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.memberRepository = springDataJpaMemberRepository;
    }


    public List<MemberResponseDto> members() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();

        for (Member member : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setId(member.getId());
            memberResponseDto.setName(member.getName());
            memberResponseDto.setEmail(member.getEmail());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    //Transactional 어노테이션 클래스 단위로 붙이면 모든 메서드에 각각 Transaction 적용
    //Transactional을 적용하면 한 메서드 단위로 트랜잭션 지정
    @Transactional
    public void memberCreate(MemberRequestDto memberRequestDto) throws IllegalArgumentException {
        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
        memberRepository.save(member);
//        트랜잭션테스트 롤백 및 예외 발생
//        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
//        memberRepository.save(member);
//        if(member.getName().equals("kim")){
//            throw new IllegalArgumentException();
//        }
    }

    public MemberResponseDto findById(int id) throws EntityNotFoundException {
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원 입니다."));
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setEmail(member.getEmail());
        memberResponseDto.setPassword(member.getPassword());
        memberResponseDto.setCreate_time(member.getCreate_time());
        return memberResponseDto;
    }

    public void MemberDelete(int id) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        memberRepository.delete(member);
    }

//    업데이트는 따로 springData에서 없어서 save메소드를 사용하면 되는데 create랑 구분하는 방법은
//    보통 PK가 있으면 업데이트, 없으면 save(create)로 보면 된다.
    public void memberUpdate(MemberRequestDto memberRequestDto) throws EntityNotFoundException {
        Member member = memberRepository.findById(memberRequestDto.getId()).orElseThrow(EntityNotFoundException::new);
        member.updateMember(memberRequestDto.getName(), memberRequestDto.getPassword());
        memberRepository.save(member);
    }
}
