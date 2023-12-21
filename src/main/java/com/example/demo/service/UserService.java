package com.example.demo.service;

import com.example.demo.domain.user.Authority;
import com.example.demo.domain.user.Member;

import java.util.List;


public interface UserService {
    Member findUsername(String username);

    Member findEmail(String email);


    boolean exist(String username);
    boolean existEmail(String email);


    int signup(Member member);
    int removeById(Long id);

    int updateById(Long id);

    List<Authority> selectAuthoritiesById(Long id);


}
