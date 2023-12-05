package com.example.demo.service;

import com.example.demo.domain.Authority;
import com.example.demo.domain.Member;

import java.util.List;

public interface UserService {
    Member findUsername(String username);


    boolean exist(String username);


    int signup(Member member);

    List<Authority> selectAuthoritiesById(Long id);


}
