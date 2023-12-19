package com.example.demo.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostPage {

    private String sort;
    private String category;
    private Long cnt;
    private int page;
    private int totalPage;
    private int pageRows;

    private String url;
    private int writePages;
    private int startPage;
    private int endPage;

    private List<Post> posts;
}
