package com.example.demo.controller.board;


import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.PostPage;
import com.example.demo.domain.board.U;
import com.example.demo.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @GetMapping("/findPost/{username}")
    public ResponseEntity<List<Post>> findByUsername(
            @PathVariable String username
    ){
        System.out.println(username);
        return new ResponseEntity<>(postService.findByUsername(username),HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public Post detail(@PathVariable Long id){
        return postService.detail(id);
    }

    @GetMapping("/{category}")
    public ResponseEntity<PostPage> list(
            Integer page,
            Integer pageRows,
            @RequestParam(defaultValue = "id") String sort,
            @PathVariable String category
            ){
        U.getSession().setAttribute("pageRows",pageRows);
        return new ResponseEntity<>(postService.list2(page,category,sort), HttpStatus.OK);
    }

    @GetMapping("/{category}/search")
    public ResponseEntity<PostPage> key(
            String keyword,
            Integer page,
            Integer pageRows,
            @RequestParam(defaultValue = "id") String sort,
            @PathVariable String category,
            Model model
    ){
        U.getSession().setAttribute("pageRows",pageRows);
        return new ResponseEntity<>(postService.findListByKeyWord(keyword,page,model,category,sort),HttpStatus.OK);
    }

}
