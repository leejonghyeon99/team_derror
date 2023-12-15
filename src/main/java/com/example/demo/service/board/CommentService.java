package com.example.demo.service.board;

import com.example.demo.domain.board.Comment;
import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;

public interface CommentService {
    // 게시글번호의 부모 댓글 목록
    QryCommentList listbyParents(Long postId);

    //부모 댓글번호의 자식 댓글 목록
    QryCommentList listbyChilds(Long commentId);

    // 특정 글(postId) 에 특정 사용자(userId) 가 댓글 작성
    QryResult writeParentComment(Long memberId, Long postId, String content);

    QryResult writeChildComment(Long memberId, Long postId, Long commentId, String content);

    // 특정 댓글(id) 삭제
    QryResult delete(Long id);

}
