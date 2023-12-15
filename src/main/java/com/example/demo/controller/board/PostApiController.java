package com.example.demo.controller.board;


import com.example.demo.domain.board.PostPage;
import com.example.demo.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/api")
public class PostApiController {

    private PostService postService;

    @Autowired
    public PostApiController(PostService postService) {
        this.postService = postService;
    }



    @GetMapping("/list/{category}")
    public ResponseEntity<PostPage> list(
            Integer page,
            @RequestParam(defaultValue = "id") String sort,
            @PathVariable String category
            ){

        return new ResponseEntity<>(postService.list(page,category,sort), HttpStatus.OK);
    }

}
