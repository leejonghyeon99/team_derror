package com.example.demo.repository;

import com.example.demo.domain.board.Comment;
import com.example.demo.domain.user.Member;
import com.example.demo.repository.post.CommentRepository;
import com.example.demo.service.board.CommentService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentRepositoryTest {


    private CommentRepository repository;
    private CommentService service;
    @Autowired
    public CommentRepositoryTest(SqlSession sqlSession, CommentService service) {
        this.repository = sqlSession.getMapper(CommentRepository.class);
        this.service = service;
    }


    @Test
    void saveParent() {
        Member member = Member.builder()
                .id(5L)
                .build();
        Comment comment = Comment.builder()
                .member(member)
                .postId(1L)
                .content("테스트 코드에서 작성한 1번 사용자의 1번글에 단 부모댓글")
                .build();
        System.out.println(comment.toString());
        repository.saveParent(comment);
    }

    @Test
    void saveChild() {
        Member member = Member.builder()
                .id(2L)
                .build();
        Comment comment = Comment.builder()
                .member(member)
                .postId(1L)
                .commentId(13L)
                .content("테스트 코드에서 작성한 1번 사용자의 부모댓글 13번글에 단 3번쨰 자식댓글")
                .build();

        repository.saveChild(comment);
        System.out.println("#######################"+comment.toString()+"#################");
    }
}