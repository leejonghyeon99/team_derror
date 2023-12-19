package com.example.demo.domain.board;

import com.example.demo.domain.user.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private Long id;    //  PK
    @ToString.Exclude
    private Member member;// 댓글 작성자(FK)
    private Long postId;// 어느글의 댓글(FK)
    private Long commentId; // 부모 댓글 아이디
    private String content; // 댓글 내용
    private int childCnt; //자식 댓글 개수

    private List<Comment> childComment;

    // java.time.* 객체 변환을 위한 annotation  들
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
}
