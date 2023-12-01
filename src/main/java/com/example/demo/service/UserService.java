package com.example.demo.service;

import com.example.demo.domain.Member;

public interface UserService {
    Member findUsername(String username);


    boolean exist(String username);

    // 신규 회원 등록
    int signup(Member member);
}
