package com.example.demo.controller.board;



import com.example.demo.domain.board.Comment;
import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;
import com.example.demo.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/comment")
public class CommentController {


    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 
     * @param postId 게시글 번호
     * @return 게시글 번호의 총 댓글 개수
     */
    @GetMapping("/cnt/{postId}")
    public ResponseEntity<Integer> getCnt(@PathVariable Long postId){
        return new ResponseEntity<>(commentService.commentCntByPostId(postId),HttpStatus.OK);
    }

    /**
     * @param postId 게시글 번호
     * @return 
     */
    @GetMapping("/parent/{postId}")
    public ResponseEntity<QryCommentList> parentCommentByPostId(@PathVariable Long postId){
        return new ResponseEntity<>(commentService.listbyParents(postId), HttpStatus.OK);
    }

    /**
     *
     * @param commentId 부모댓글 번호
     * @return
     */
    @GetMapping("/child/{commentId}")
    public ResponseEntity<QryCommentList> childCommentByCommentId(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.listbyChilds(commentId), HttpStatus.OK);
    }

    /**
     *
     * @param memberId 댓글 작성자 번호
     * @param postId 댓글이 작성되는 게시글 번호
     * @param content 내용
     * @return
     */
    @PostMapping("/parent/write")
    public ResponseEntity<QryResult> parentCommentSaveByPostId(
            Long memberId,
            Long postId,
            String content
    ){
        return new ResponseEntity<>(commentService.writeParentComment(memberId,postId,content),HttpStatus.OK);
    }

    /**
     *
     * @param requestBody memberId, postId, commentId, content
     * @return
     */
    @PostMapping("/child/write")
    public ResponseEntity<QryCommentList> parentCommentSaveByPostId(
            @RequestBody Map<String, Object> requestBody
    ){
        Long commentId = Long.valueOf(requestBody.get("commentId").toString());
        Long memberId = Long.valueOf(requestBody.get("memberId").toString());
        Long postId = Long.valueOf(requestBody.get("postId").toString());
        String content = (String) requestBody.get("content");
        System.out.println("test: "+memberId+", "+postId+", "+commentId+", "+content);
        QryResult qryResult = commentService.writeChildComment(memberId,postId,commentId, content);
        QryCommentList commentList = null;
        if(qryResult.getStatus().equals("OK")){
            System.out.println(qryResult.getId());
             commentList= commentService.listbyChilds(commentId);
        }
        return new ResponseEntity<>(commentList,HttpStatus.OK);
    }

    /**
     *
     * @param id 댓글 번호
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<QryResult> deleteById(Long id){
        return new ResponseEntity<>(commentService.delete(id),HttpStatus.OK);
    }

} // end controller
















