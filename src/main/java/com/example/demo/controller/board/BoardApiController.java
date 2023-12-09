package com.example.demo.controller.board;

import com.example.demo.domain.board.Post;
import com.example.demo.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board/api")
public class BoardApiController {

    private BoardService boardService;

    @Autowired
    public BoardApiController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 페이징 사용 조회수
    @GetMapping("/notice/desc")
    public ResponseEntity<List<Post>> list(Integer page,String sort, Model model){
        System.out.println(page);
          List<Post> list = boardService.listDescByViewCnt(page, model);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
