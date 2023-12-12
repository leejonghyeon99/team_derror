package com.example.demo.service.board;

// Service layer
// - Business Logic, Transaction 담당
// - Controller 와 Data 레이어의 분리

import org.springframework.transaction.annotation.Transactional;
import com.example.demo.domain.board.Post;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;
/**
 @Author 장고운
 */
public interface BoardService {

    int write(Post post, Map<String, MultipartFile> files);

    @Transactional
    Post detail(Long id);

    List<Post> list();

    List<Post> list(Integer page, Model model);

    List<Post> findByUserName(String username);

    List<Post> listDescByViewCnt(Integer page, Model model);

    Post selectById(Long id);

    int update(Post post,
               Map<String, MultipartFile> files,
               Long[] delfile);

    int deleteById(Long id);

    List<Post> searchByTitle(String keyword);
} // end Service
