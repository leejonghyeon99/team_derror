package com.example.demo.service.board;

import com.example.demo.domain.board.Comment;
import com.example.demo.domain.board.QryResult;
import com.example.demo.domain.user.Member;
import com.example.demo.repository.CommentRepository;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    private CommentRepository repository;

    @Autowired
    public CommentServiceTest(SqlSession sqlSession) {
        this.repository = sqlSession.getMapper(CommentRepository.class);
    }



}