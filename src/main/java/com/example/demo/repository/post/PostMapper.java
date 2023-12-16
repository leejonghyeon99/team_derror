package com.example.demo.repository.post;

import com.example.demo.domain.board.Post;

import java.util.List;

public interface PostMapper {

    Post findById(Long id);

    List<Post> listByRecent(int from, int rows, String category);

    List<Post> listByViewCnt(int from, int rows, String category);

    int countAll(String category);

    int viewCntUpdate(Long id);
}
