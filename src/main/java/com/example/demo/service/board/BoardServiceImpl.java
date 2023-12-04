package com.example.demo.service.board;

import com.example.demo.domain.board.Post;
import com.example.demo.repository.PostRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
/**
 @Author 장고운
 */
@Service
public class BoardServiceImpl implements BoardService {
    private PostRepository postRepository;

    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){ // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        postRepository = sqlSession.getMapper(PostRepository.class);
    }

    @Override
    public int write(Post post, Map<String, MultipartFile> files) {
        return 0;
    }

    @Override
    @Transactional  // 이 메소드는 '트랜잭션' 처리
    public Post detail(Long id) {
        postRepository.ViewCnt(id);
        Post post = postRepository.findById(id);

//        if(post != null){
//            // 첨부파일(들) 정보 가져오기
//            List<Attachment> fileList = attachmentRepository.findByPost(post.getId());
//            setImage(fileList);   // 이미지 파일 여부 세팅
//            post.setFileList(fileList);
//        }

        return post;
    }

    @Override
    public List<Post> list(Integer page, Model model) {
        return null;
    }

    @Override
    public Post selectById(Long id) {
        return null;
    }

    @Override
    public int update(Post post, Map<String, MultipartFile> files, Long[] delfile) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
