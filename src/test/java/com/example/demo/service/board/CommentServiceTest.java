package com.example.demo.service.board;

import com.example.demo.repository.post.CommentRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentServiceTest {

    private CommentRepository repository;

    @Autowired
    public CommentServiceTest(SqlSession sqlSession) {
        this.repository = sqlSession.getMapper(CommentRepository.class);
    }



}