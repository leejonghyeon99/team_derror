package com.example.demo.repository;

import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.U;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {


    private PostRepository postRepository;

    @Autowired
    public PostRepositoryTest(SqlSession sqlSession) {
        this.postRepository = sqlSession.getMapper(PostRepository.class);
    }

    @Test
    public void test(){
        List<Post> posts = null;
        posts = postRepository.findAll();

        posts.stream().forEach(System.out::println);
    }

    @Test
    public void test2(int from, int rows, String category) {

        List<Post> posts = null;
        posts = postRepository.selectFromRow(from, rows, "notice");

        posts.stream().forEach(System.out::println);

    }
}