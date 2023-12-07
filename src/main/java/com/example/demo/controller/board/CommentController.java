package com.example.demo.controller.board;



import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;
import com.example.demo.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam("post_id") Long postId,
            @RequestParam("member_id") Long memberId,
            String content
    ){
        return commentService.write(postId, memberId, content);
    }


    @PostMapping("/delete")
    public QryResult delete(Long id) {
        return commentService.delete(id);
    }

}















