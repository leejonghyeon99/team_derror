package com.example.demo.controller.board;

import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;
import com.example.demo.service.board.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentControllerTest {



    @Autowired
    private CommentService commentService;

    @Test
    public void list() {
        System.out.println("나 테스트 메소드임");
        System.out.println( commentService.list(4L).getList());
    }


}