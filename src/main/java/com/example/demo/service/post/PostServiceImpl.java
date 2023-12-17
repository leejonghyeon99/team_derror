package com.example.demo.service.post;

import com.example.demo.domain.board.Attachment;
import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.PostPage;
import com.example.demo.domain.board.U;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.repository.post.PostMapper;
import com.example.demo.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    @Value("${app.upload.path}")
    private String uploadDir;

    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;

    private PostMapper postMapper;
    private UserRepository userRepository;
    private AttachmentRepository attachmentRepository;


    @Autowired
    public PostServiceImpl(SqlSession sqlSession){ // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        this.postMapper = sqlSession.getMapper(PostMapper.class);
        this.userRepository = sqlSession.getMapper(UserRepository.class);
        this.attachmentRepository = sqlSession.getMapper(AttachmentRepository.class);
    }

    @Override
    @Transactional  // 이 메소드는 '트랜잭션' 처리
    public Post detail(Long id) {
        postMapper.viewCntUpdate(id);
        Post post = postMapper.findById(id);

        if(post != null){
            // 첨부파일(들) 정보 가져오기
            List<Attachment> fileList = attachmentRepository.findByPost(post.getId());
            System.out.println(fileList);
            setImage(fileList);   // 이미지 파일 여부 세팅
            post.setFileList(fileList);
        }

        return post;
    }

    private void setImage(List<Attachment> fileList) {
        // upload 실제 물리적인 경로
        String realPath = new File(uploadDir).getAbsolutePath();
        System.out.println("setImage test: "+ fileList.toString());
        for(var attachment : fileList){
            BufferedImage imgData = null;
            File f = new File(realPath, attachment.getFilename());  // 저장된 첨부파일에 대한 File 객체

            try {
                imgData = ImageIO.read(f);
            } catch (IOException e) {
                System.out.println("파일존재안함: " + f.getAbsolutePath() + "[" + e.getMessage() + "]");
                throw new RuntimeException(e);
            }

            if(imgData != null) attachment.setImage(true);  // 이미지 여부 체크!
        }
    }

    @Override
    public PostPage list(Integer page, String category, String sort, Model model) {
        if(page == null) page = 1;  // 디폴트는 1page
        if(page < 1) page = 1;

        // 페이징
        // writePages: 한 [페이징] 당 몇개의 페이지가 표시되나
        // pageRows: 한 '페이지'에 몇개의 글을 리스트 할것인가?
        HttpSession session = U.getSession();
        Integer writePages = (Integer)session.getAttribute("writePages");
        if(writePages == null) writePages = WRITE_PAGES;  // 만약 session 에 없으면 기본값으로 동작
        Integer pageRows = (Integer)session.getAttribute("pageRows");
        if(pageRows == null) pageRows = PAGE_ROWS;  // 만약 session 에 없으면 기본값으로 동작

        // 현재 페이지 번호 -> session 에 저장
        session.setAttribute("page", page);
        session.setAttribute("category",category);
        session.setAttribute("sort", sort);

        long cnt = postMapper.countAll(category);   // 글 목록 전체의 개수
        int totalPage = (int)Math.ceil(cnt / (double)pageRows);   // 총 몇 '페이지' ?

        // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지'
        int startPage = 0;
        int endPage = 0;

        // 해당 페이지의 글 목록
        List<Post> list = null;

        if(cnt > 0){  // 데이터가 최소 1개 이상 있는 경우만 페이징
            //  page 값 보정
            if(page > totalPage) page = totalPage;

            // 몇번째 데이터부터 fromRow
            int fromRow = (page - 1) * pageRows;

            // [페이징] 에 표시할 '시작페이지' 와 '마지막페이지' 계산
            startPage = (((page - 1) / writePages) * writePages) + 1;
            endPage = startPage + writePages - 1;
            if (endPage >= totalPage) endPage = totalPage;

            if(sort.equals("id")){
                list = postMapper.listByRecent(fromRow, pageRows, category);
            }else {
                list = postMapper.listByViewCnt(fromRow,pageRows,category);
            }



        } else {
            page = 0;
        }

        PostPage postPage = PostPage.builder()
                .sort(sort)
                .category(category)
                .cnt(cnt)
                .page(page)
                .totalPage(totalPage)
                .pageRows(pageRows)
                .url(U.getRequest().getRequestURI())
                .writePages(writePages)
                .startPage(startPage)
                .endPage(endPage)
                .posts(list)
                .build();

        model.addAttribute("postPage",postPage);
        return postPage;
    }


}
