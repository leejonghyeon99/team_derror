package com.example.demo.controller.board;

import com.example.demo.domain.board.U;
import com.example.demo.service.board.BoardService;
import com.example.demo.service.board.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardControllerTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void pageRows(){
        System.out.println("작동 가능?"+ U.getSession().toString());
    }
}