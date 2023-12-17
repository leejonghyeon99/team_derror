package com.example.demo.service.board;

import com.example.demo.domain.board.Comment;
import com.example.demo.domain.board.QryCommentList;
import com.example.demo.domain.board.QryResult;
import com.example.demo.domain.user.Member;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);

    }


    /**
     * 
     * @param id 게시글 번호
     * @return 게시글번호의 댓글 총 개수
     */
    @Override
    public int commentCntByPostId(Long id) {
        return commentRepository.commentCnt(id);
    }

    /**
     *
     * @param postId 게시글 번호(id)
     * @return list[게시글의 부모 댓글 목록 반환]
     */
    @Override
    public QryCommentList listbyParents(Long postId) {
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findAllParents(postId);

        list.setCount(comments.size());
        list.setList(comments);
        list.setStatus("OK");
        return list;
    }

    /**
     *
     * @param commentId 부모 댓글 번호
     * @return list[부모 댓글의 자식댓글 목록 반환]
     */
    @Override
    public QryCommentList listbyChilds(Long commentId) {
        QryCommentList list = new QryCommentList();

        List<Comment> comments = commentRepository.findAllChilds(commentId);

        list.setCount(comments.size());
        list.setList(comments);
        list.setStatus("OK");
        return list;
    }

    /**
     *
     * @param memberId
     * @param postId
     * @param content
     * @return
     */
    @Transactional
    @Override
    public QryResult writeParentComment(Long memberId, Long postId, String content) {
        try {
            Member member = userRepository.findId(memberId);
            Comment comment = Comment.builder()
                    .member(member)
                    .postId(postId)
                    .content(content)
                    .build();
            commentRepository.saveParent(comment);

            return QryResult.builder()
                    .count(1)
                    .status("OK")
                    .build();
        } catch (DataAccessException e) {
            // 예외 처리
            log.error("부모 댓글 작성 에러", e);

            return QryResult.builder()
                    .count(0)
                    .status("FAIL")
                    .build();
        }
    }

    /**
     *
     * @param memberId
     * @param postId
     * @param commentId
     * @param content
     * @return
     */
    @Override
    public QryResult writeChildComment(Long memberId, Long postId, Long commentId, String content) {
        try {
            Member member = userRepository.findId(memberId);
            Comment comment = Comment.builder()
                    .member(member)
                    .postId(postId)
                    .commentId(commentId)
                    .content(content)
                    .build();
            commentRepository.saveChild(comment);

            return QryResult.builder()
                    .count(1)
                    .status("OK")
                    .build();
        } catch (DataAccessException e) {
            // 예외 처리
            log.error("자식 댓글 작성 에러", e);

            return QryResult.builder()
                    .count(0)
                    .status("FAIL")
                    .build();
        }
    }


    /**
     * 
     * @param id 삭제할 댓글 번호
     * @return
     */
    @Override
    public QryResult delete(Long id) {
        int count = 0;
        try {
            count = commentRepository.deleteById(id);
            return QryResult.builder()
                    .count(count)
                    .status("OK")
                    .build();

        }catch (DataAccessException  e){
            return QryResult.builder()
                    .count(count)
                    .status("OK")
                    .build();
        }
    }


}// end serviceimpl














