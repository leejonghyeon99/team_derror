package com.example.demo.repository;

import com.example.demo.domain.Authority;
import com.example.demo.domain.Member;

import java.util.List;

public interface AuthorityRepository {

    Authority findName(String name);
    List<Authority> findUser(Member member);

    int addAuthority(Long user_id,Long auth_id);
}
