package com.example.demo.controller.board;



import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;
import com.example.demo.service.board.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/comment")
public class CommentController {


    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
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
     * @param memberId 댓글 작성자 번호
     * @param postId 댓글이 작성되는 게시글 번호
     * @param commentId 댓글이 작성되는 부모 댓글 번호
     * @param content 내용
     * @return
     */
    @PostMapping("/child/write")
    public ResponseEntity<QryResult> parentCommentSaveByPostId(
            Long memberId,
            Long postId,
            Long commentId,
            String content
    ){
        return new ResponseEntity<>(commentService.writeChildComment(memberId,postId,commentId, content),HttpStatus.OK);
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
















