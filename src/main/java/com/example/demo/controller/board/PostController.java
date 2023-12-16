package com.example.demo.controller.board;

import com.example.demo.domain.board.U;
import com.example.demo.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {


    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id){
        model.addAttribute("post",postService.detail(id));
        return "post/detail";
    }

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute("category",U.getSession().getAttribute("category"));
        model.addAttribute("sort",U.getSession().getAttribute("sort"));
    }

}
