package com.example.demo.domain.board;

import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDateTime regDate;  // 작성일자
    private Long viewCnt;   // 조회수



}
