package com.example.demo.repository.post;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostMapperTest {


    private PostMapper mapper;

    @Autowired
    public PostMapperTest(SqlSession sqlSession) {
        this.mapper = sqlSession.getMapper(PostMapper.class);
    }

    @Test
    public void test(){
        System.out.println(mapper.listByRecent(0,2,"notice").toString());
    }

}