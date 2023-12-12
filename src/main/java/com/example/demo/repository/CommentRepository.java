package com.example.demo.repository;

import com.example.demo.domain.board.Comment;

import java.util.List;

public interface CommentRepository {
    // 특정 글(post_id) 의 댓글 목록
    List<Comment> findByPost(Long post_id);

    // 댓글 작성 <-- Comment
    int save(Comment comment);

    // 특정 댓글 (id) 삭제
    int deleteById(Long id);

    // 특정 글(comment_id) 의 댓글 목록
    List<Comment> findByComment(Long comment_id);

    // 대댓글 작성
    int replySave(Comment comment);
}
