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

    @GetMapping("/free")
    public void free(Model model){
        model.addAttribute("category","free");
        model.addAttribute("pageRows","free");
        model.addAttribute("sort",U.getSession().getAttribute("sort"));
    }

    @GetMapping("/notice")
    public void notice(Model model){
        model.addAttribute("category","notice");
        model.addAttribute("sort",U.getSession().getAttribute("sort"));
    }

}
