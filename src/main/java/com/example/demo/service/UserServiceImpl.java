package com.example.demo.service;

import com.example.demo.domain.board.Attachment;
import com.example.demo.domain.board.Post;
import com.example.demo.domain.user.Authority;
import com.example.demo.domain.user.Member;
import com.example.demo.repository.user.AuthorityRepository;
import com.example.demo.repository.user.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;



    @Autowired
    public UserServiceImpl(SqlSession sqlSession){
        userRepository = sqlSession.getMapper(UserRepository.class);
        authorityRepository = sqlSession.getMapper(AuthorityRepository.class);
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
        member.setAuth_id(1L);  // 권한설정
        userRepository.save(member);

        return 1;
    }


    @Override
    public int removeById(Long id){

      Member member = userRepository.findId(id);
       if(member != null){
           userRepository.remove(member);
           return 1;
       }
        return 0;
    }
    @Override
    public List<Authority> selectAuthoritiesById(Long id) {

        Member member = userRepository.findId(id);
        return authorityRepository.findUser(member);
    }
}
