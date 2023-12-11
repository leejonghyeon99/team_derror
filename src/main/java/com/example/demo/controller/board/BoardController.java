package com.example.demo.controller.board;


import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.PostValidator;
import com.example.demo.domain.board.U;
import com.example.demo.service.board.BoardService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


/**
 * @Author 장고운
 */
@Controller
@RequestMapping("/board")
public class BoardController {


    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 작성페이지
    @GetMapping("/write")
    public void write(){}

    // 작성완료
    @PostMapping("/write")
    public String writeOk(
            @RequestParam Map<String, MultipartFile> files   // 첨부 파일
            , @Valid Post post
            , BindingResult result
            , Model model   // 매개변수 선언시 BindingResult 보다 Model 을 뒤에 두어야 한다.
            , RedirectAttributes redirectAttrs
    ){
        // validation 에러가 있었다면 redirect 할거다!
        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("title", post.getTitle());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            for(var err : result.getFieldErrors()){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/write";
        }

        model.addAttribute("result", boardService.write(post, files));
        return "board/writeOk";
    }

    // 세부사항
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        Post detail = boardService.detail(id);
        model.addAttribute("post", detail);
        return "board/detail";
    }

    // 페이징 사용 공지 최신순
    @GetMapping("/notice")
    public void list(String sort, Integer page, Model model){
        System.out.println(sort);
        boardService.list(page, model);

    }

    // 페이징 사용 공지 조회순
    @GetMapping("/notice/desc")
    public String listDesc(Integer page,String sort, Model model){
        System.out.println(sort);
        boardService.listDescByViewCnt(page, model);
        return "board/notice";
    }

    // 공유 최신순
    @GetMapping("/share")
    public void share(String sort, Integer page, Model model){
        System.out.println(sort);
        boardService.list(page, model);

    }

    // 페이징 - pageRows 변경시 동작
    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/board/notice?page=" + page;
    }

    // 수정페이지
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        Post post = boardService.selectById(id);
        model.addAttribute("post", post);
        return "board/update";
    }

    // 수정완료
    @PostMapping("/update")
    public String updateOk(
            @RequestParam Map<String, MultipartFile> files  // 새로 추가될 첨부파일들
            , Long[] delfile    // 삭제될 파일들
            , @Valid Post post
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ){
        if(result.hasErrors()){

            redirectAttrs.addFlashAttribute("title", post.getTitle());
            redirectAttrs.addFlashAttribute("content", post.getContent());

            for(var err : result.getFieldErrors()){
                redirectAttrs.addFlashAttribute("error_" + err.getField(), err.getCode());
            }

            return "redirect:/board/update/" + post.getId();
        }


        model.addAttribute("result", boardService.update(post, files, delfile));
        return "board/updateOk";
    }

    // 삭제
    @PostMapping("/delete")
    public String deleteOk(Long id, Model model){
        int result = boardService.deleteById(id);
        model.addAttribute("result", result);
        return "board/deleteOk";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new PostValidator());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchByTitle(@RequestParam("keyword") String keyword) {
        // 키워드를 이용하여 게시글의 제목을 검색하는 서비스 메서드 호출
        List<Post> searchResult = boardService.searchByTitle(keyword);

        // 검색 결과를 클라이언트에 반환
        return ResponseEntity.ok(searchResult);
    }


}   // end Controller
