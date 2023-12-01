package com.example.demo.repository;

import com.example.demo.domain.Member;

public interface UserRepository {
    Member findId(Long id);
    Member findUsername(String username);
    int save(Member member);
    int update(Member member);
}
