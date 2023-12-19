package com.example.demo.repository;

import com.example.demo.domain.board.Comment;

import java.util.List;

public interface CommentRepository {

    Comment getCommentById(Long id);

    //게시글번호의 댓글 총 갯수
    int commentCnt(Long id);

    // 게시글 번호의 부모 댓글 목록
    List<Comment> findAllParents(Long postId);

    //부모 댓글 번호의 자식 댓글 목록(대댓글)
    List<Comment> findAllChilds(Long commentId);


    // 댓글 작성 <-- Comment
    int saveParent(Comment comment);

    // 대댓글 작성
    int saveChild(Comment comment);


    // 특정 댓글 (id) 삭제
    int deleteById(Long id);

}
