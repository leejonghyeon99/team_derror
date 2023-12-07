package com.example.demo.repository;

import com.example.demo.domain.user.Member;
import com.example.demo.repository.user.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

    private UserRepository repository;

    @Autowired
    public UserRepositoryTest(SqlSession sqlSession) {
        this.repository = sqlSession.getMapper(UserRepository.class);
    }

    @Test
    public void test(){
        Member member = null;
        member = repository.findUsername("USER1");
        System.out.println(member);
    }

}