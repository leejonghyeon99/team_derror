package com.example.demo.repository.user;

import com.example.demo.domain.user.Member;

public interface UserRepository {
    Member findId(Long id);
    Member findUsername(String username);
    int save(Member member);
    int update(Member member);
}
