package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

@Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);

    }

    @Override
    public Member findUsername(String username) {
        return userRepository.findUsername(username);
    }

    @Override
    public boolean exist(String username) {
        Member member = findUsername(username);
        return ( member != null)? true : false;
    }

    @Override
    public int signup(Member member) {

        member.setUsername(member.getUsername().toUpperCase());

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        userRepository.save(member);
        return 1;
    }
}
