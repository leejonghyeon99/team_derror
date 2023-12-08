package com.example.demo.controller.board;



import com.example.demo.domain.board.Comment;
import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;
import com.example.demo.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController   // data 를 response 한다 ('View' 를 리턴하는게 아니다!)
// @Controller + @ResponseBody 와 같다.
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public QryCommentList list(Long id) {
        return commentService.list(id);
    }

    @PostMapping("/write")
    public QryResult write(
//            @RequestBody Comment comment
            @RequestParam("post_id") Long postId,
            @RequestParam("user_id") Long userId,
            String content

    ){
//        System.out.println(comment);
//        System.out.println(post_id +","+user_id+","+content);
        return commentService.write(postId, userId, content);

    }


    @PostMapping("/delete")
    public QryResult delete(Long id) {
        return commentService.delete(id);
    }

}
















