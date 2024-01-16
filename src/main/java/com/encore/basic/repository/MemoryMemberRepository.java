package com.encore.basic.repository;


import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    private final List<Member> memberDB;

    public MemoryMemberRepository(){
        memberDB = new ArrayList<>();
    }

    @Override
    public List<Member> findAll(){
        return memberDB;
    }
    @Override
    public Member save(Member member){
        memberDB.add(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
        for (Member member : memberDB){
            if (member.getId() == id){
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
}
