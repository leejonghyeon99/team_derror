package com.example.demo.service;

import com.example.demo.domain.user.Authority;
import com.example.demo.domain.user.Member;

import java.util.List;


public interface UserService {
    Member findUsername(String username);


    boolean exist(String username);


    int signup(Member member);
    int removeById(Long id);

    List<Authority> selectAuthoritiesById(Long id);


}
