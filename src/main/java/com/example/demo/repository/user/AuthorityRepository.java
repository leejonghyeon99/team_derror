package com.example.demo.repository.user;

import com.example.demo.domain.user.Authority;
import com.example.demo.domain.user.Member;

import java.util.List;

public interface AuthorityRepository {

    Authority findName(String name);
    List<Authority> findUser(Member member);

    int addAuthority(Long user_id,Long auth_id);
}
