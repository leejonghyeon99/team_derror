package com.example.demo.service.board;

import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;

public interface CommentService {
    // 특정 글(id) 의 댓글 목록
    QryCommentList list(Long id);

    // 특정 글(postId) 에 특정 사용자(userId) 가 댓글 작성
    QryResult write(Long postId, Long memberId, String content);

    // 특정 댓글(id) 삭제
    QryResult delete(Long id);

}
