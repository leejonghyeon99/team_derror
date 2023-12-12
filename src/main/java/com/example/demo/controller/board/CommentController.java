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

    // 댓글 목록
    @GetMapping("/list")
    public QryCommentList list(Long id) {
        return commentService.list(id);
    }

    // 댓글 작성
    @PostMapping("/write")
    public QryResult write(
            @RequestParam("post_id") Long postId,
            @RequestParam("user_id") Long userId,
            String content

    ){
        return commentService.write(postId, userId, content);

    }


    // 댓글 삭제
    @PostMapping("/delete")
    public QryResult delete(Long id) {
        return commentService.delete(id);
    }

    // 대댓글 작성
    @PostMapping("/reply")
    public QryResult write(
            @RequestParam("post_id") Long postId,
            @RequestParam("parent_id") Long parentId,
            @RequestParam("user_id") Long userId,
            String content

    ){
        return commentService.replyWrite(postId, parentId, userId, content);
    }


} // end controller
















