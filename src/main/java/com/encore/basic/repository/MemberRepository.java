package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public List<Member> findAll();
    public Member save(Member member);
    Optional<Member> findById(int id);
}
