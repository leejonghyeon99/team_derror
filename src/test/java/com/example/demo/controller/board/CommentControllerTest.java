package com.example.demo.controller.board;

import com.example.demo.service.board.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentControllerTest {



    @Autowired
    private CommentService commentService;

    @Test
    public void list() {
        System.out.println("나 테스트 메소드임");
        System.out.println( commentService.listbyParents(4L).getList());
    }


}