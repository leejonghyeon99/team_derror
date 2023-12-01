package com.example.demo.service;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Member findUsername(String username) {
        return null;
    }

    @Override
    public boolean exist(String username) {
        return false;
    }

    @Override
    public int signup(Member member) {
        return 0;
    }
}
