package com.example.demo.service.post;

import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.PostPage;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PostService {

    int write(Post post, Map<String, MultipartFile> files);

    List<Post> findByUsername(String username);

    Post detail(Long id);

    Post findById(Long id);

    void list(Integer page, String category, String sort, Model model);

    PostPage list2(Integer page, String category, String sort);

    int update(Post post,
               Map<String, MultipartFile> files,
               Long[] delfile);

    int deleteById(Long id);

    PostPage findListByKeyWord(String keyword, Integer page, Model model, String category, String sort);
}
