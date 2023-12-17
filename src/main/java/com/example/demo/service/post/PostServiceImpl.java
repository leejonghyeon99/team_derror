package com.example.demo.service.post;

import com.example.demo.domain.board.Attachment;
import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.PostPage;
import com.example.demo.domain.board.U;
import com.example.demo.domain.user.Member;
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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

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
    public int write(Post post, Map<String, MultipartFile> files) {
        // 현재 로그인한 작성자 정보.
        Member member = U.getLoggedUser();

        // 위 정보는 session 의 정보이고, 일단 DB 에서 다시 읽어온다
        member = userRepository.findId(member.getId());
        post.setMember(member);   // 글 작성자 세팅

        int cnt = postMapper.save(post);

        // 첨부파일 추가
        addFiles(files, post.getId());

        return cnt;
    }

    // 특정 글(id) 첨부파일(들) 추가
    private void addFiles(Map<String, MultipartFile> files, Long id) {
        if(files != null){
            for(var e : files.entrySet()){

                // name="upfile##" 인 경우만 첨부파일 등록. (이유, 다른 웹에디터와 섞이지 않도록..ex: summernote)
                if(!e.getKey().startsWith("upfile")) continue;

                // 첨부 파일 정보 출력
                System.out.println("\n첨부파일 정보: " + e.getKey());   // name값
                U.printFileInfo(e.getValue());   // 파일 정보 출력
                System.out.println();

                // 물리적인 파일 저장
                Attachment file = upload(e.getValue());

                // 성공하면 DB 에도 저장
                if(file != null){
                    file.setPostId(id);   // FK 설정
                    attachmentRepository.save(file);   // INSERT
                }
            }
            Attachment attachment = attachmentRepository.findById(id);
            if (attachment != null){
                //첫번째 이미지를 썸네일 설정
                attachmentRepository.updateForThumbnail(id);
            }
        }
    } // end addFiles()

    private Attachment upload(MultipartFile multipartFile) {

        Attachment attachment = null;

        // 담긴 파일이 없으면 pass
        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null || originalFilename.length() == 0) return null;

        // 원본파일명
        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        // 저장될 파일명
        String fileName = sourceName;

        // 파일명 이 중복되는지 확인
        File file = new File(uploadDir, sourceName);

        if(file.exists()){  // 이미 존재하는 파일명,  중복되면 다름 이름으로 변경하여 저장
            // a.txt => a_2378142783946.txt  : time stamp 값을 활용할거다!
            int pos = fileName.lastIndexOf(".");
            if(pos > -1){   // 확장자가 있는 경우
                String name = fileName.substring(0, pos);  // 파일 '이름'
                String ext = fileName.substring(pos + 1);   // 파일 '확장자'

                // 중복방지를 위한 새로운 이름 (현재시간 ms) 를 파일명에 추가
                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {  // 확장자가 없는 경우
                fileName += "_" + System.currentTimeMillis();
            }
        }
        // 저장할 파일명
        System.out.println("fileName: " + fileName);

        // java.nio
        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());
        System.out.println(copyOfLocation);

        try {
            // inputStream을 가져와서
            // copyOfLocation (저장위치)로 파일을 쓴다.
            // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다

            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING    // 기존에 존재하면 덮어쓰기
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        attachment = Attachment.builder()
                .filename(fileName)   // 저장된 이름
                .sourcename(sourceName)  // 원본 이름
                .build();

        return attachment;
    }

    @Override
    @Transactional  // 이 메소드는 '트랜잭션' 처리
    public Post detail(Long id) {
        postMapper.viewCntUpdate(id);
        Post post = postMapper.findById(id);

        if(post != null){
            // 첨부파일(들) 정보 가져오기
            List<Attachment> fileList = attachmentRepository.findByPost(id);
            System.out.println(fileList);
            setImage(fileList);   // 이미지 파일 여부 세팅
            post.setFileList(fileList);
        }

        return post;
    }

    @Override
    public Post findById(Long id) {
        Post post = postMapper.findById(id);

        if(post != null){
            // 첨부파일 정보 가져오기
            List<Attachment> fileList = attachmentRepository.findByPost(id);
            setImage(fileList);   // 이미지 파일 여부 세팅
            post.setFileList(fileList);
        }
        return post;
    }

    private void setImage(List<Attachment> fileList) {
        // upload 실제 물리적인 경로
        String realPath = new File(uploadDir).getAbsolutePath();

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
    public void list(Integer page, String category, String sort, Model model) {
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

    }


    @Override
    public PostPage list2(Integer page, String category, String sort) {
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


        return postPage;
    }

    @Override
    public int update(Post post, Map<String, MultipartFile> files, Long[] delfile) {
        int result = postMapper.update(post);

        // 새로운 첨부파일 추가
        addFiles(files, post.getId());

        // 삭제할 첨부파일(들) 삭제
        if(delfile != null){
            for(Long fileId : delfile){
                Attachment file = attachmentRepository.findById(fileId);
                if(file != null){
                    delFile(file, post.getId());  // 물리적으로 파일 삭제
                    attachmentRepository.delete(file);  // DB 에서 삭제
                }
            }
        }
        return result;
    }

    private void delFile(Attachment file, Long id) {
        String saveDirectory = new File(uploadDir).getAbsolutePath();
        File f = new File(saveDirectory, file.getFilename());   // 물리적으로 저장된 파일들이 삭제 대상
        System.out.println("삭제시도--> " + f.getAbsolutePath());

        if(f.exists()){
            if (f.delete()){
                if (attachmentRepository.delete(file) > 0){
                    attachmentRepository.updateForThumbnailWhenDelete(id);
                    System.out.println("삭제 성공");
                }else {
                    System.out.println("Attach Table 데이터 삭제 실패");
                }
            } else {
                System.out.println("삭제 실패");
            }
        } else {
            System.out.println("파일이 존재하지 않습니다.");
        }
    }


    @Override
    public int deleteById(Long id) {
        int result = 0;
        Post post = postMapper.findById(id);  // 존재하는 데이터인지 읽어와보기
        if(post != null){  // 존재한다면 삭제 진행.
            // 물리적으로 저장된 첨부파일(들) 삭제
            List<Attachment> fileList = attachmentRepository.findByPost(id);
            if(fileList != null){
                for(Attachment file : fileList){
                    delFile(file,id);
                }
            }

            result = postMapper.delete(id);
        }
        return result;
    }

    @Override
    public PostPage findListByKeyWord(String keyword, Integer page, Model model, String category, String sort) {
        // 현재 페이지 parameter
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

        long cnt = postMapper.countKeyword(keyword, category);   // 글 목록 전체의 개수
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

            // 해당페이지의 글 목록 읽어오기
            if (keyword == null || keyword.trim().isEmpty()) {
                model.addAttribute("noSearchResults", true);

            } else {
                list = postMapper.findListByKeyWord(keyword,fromRow, pageRows, category);
            }

        } else {
            page = 0;
            model.addAttribute("noSearchResults", true);
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
