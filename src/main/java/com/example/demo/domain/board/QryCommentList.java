package com.example.demo.domain.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
//부모 쪽까지 불러오는거 끄기
public class QryCommentList extends QryResult {
    @ToString.Exclude
    List<Comment> list;
}
