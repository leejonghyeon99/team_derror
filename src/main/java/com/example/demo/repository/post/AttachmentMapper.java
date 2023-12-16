package com.example.demo.repository.post;

import com.example.demo.domain.board.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentMapper {

    int insert(List<Map<String, Object>> list, Long postId);

    // 첨부파일 저장
    int save(Attachment file);

    // 특정 글(postId) 의 첨부파일들
    List<Attachment> findByPost(Long postId);

    // 특정 첨부파일(id) 한개 SELECT
    Attachment findById(Long id);

    // 선택된 첨부파일들 DELETE
    // 글 '수정' 단계에서 사용
    int deleteByIds(Long [] ids);

    // 선택된 첨부파일들 SELECT
    // 글 '수정' 단계에서 사용
    List<Attachment> findByIds(Long [] ids);

    // 특정 첨부파일(file) 을 DB 에서 삭제
    int delete(Attachment file);
}
