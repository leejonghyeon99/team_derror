package com.example.demo.service.post;

import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.PostPage;
import org.springframework.ui.Model;

import java.util.List;

public interface PostService {

    Post detail(Long id);
    PostPage list(Integer page, String category, String sort);


}
