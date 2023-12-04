package com.example.demo.domain.board;

import com.example.demo.domain.Member;
import lombok.*;
import java.time.LocalDateTime;

/**
@Author 장고운
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long id;    // 글 id
    private Long memberId;  // 회원 id
    private String category;    // 카테고리
    private String title;   // 제목
    private String content; // 내용
    private LocalDateTime createdDate;  // 작성일자
    private Long viewCnt;   // 조회수

    private Member member;  // 글 작성자 (FK)



}
