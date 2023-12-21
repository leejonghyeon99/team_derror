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
@RequestMapping("/sample")
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

    // 리스트 최신순 리스트 조회순 내림차순
    @GetMapping("/{category}")
    public String list(Integer page, Model model, @PathVariable String category, String sort){
        model.addAttribute("category", category);
        model.addAttribute("sort", sort);
        boardService.list(page, model, category, sort);
        System.out.println("페이징 리스트"+","+page+","+category+","+sort);
        return "board/list";
    }

//    // 리스트 최신순 리스트 조회순 오름차순
//    @GetMapping("/{category}/desc")
//    public String listDesc(Integer page, Model model, @PathVariable String category){
//        boardService.listDescByViewCnt(page, model, category);
//        return "board/list";
//    }

    // 페이징 버튼 클릭 시 동작
    @PostMapping("/{category}/pageRows")
    public String pageRows(@PathVariable String category, Integer page, Integer pageRows){
        System.out.println("페이징 버튼 클릭"+","+category+","+page+","+pageRows);
        U.getSession().setAttribute("pageRows", pageRows);
        return "redirect:/board/"+category+"?page=" + page;
    }
//    // 페이징 - pageRows 변경시 동작
//    @PostMapping("/{category}/pageRows")
//    public String viewCntpageRows(@PathVariable String category, Integer page, Integer pageRows){
//        System.out.println(category+","+page+","+pageRows);
//        U.getSession().setAttribute("pageRows", pageRows);
//        return "redirect:/board/"+category+"desc?page=" + page;
//    }


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
    public String deleteOk(Long postId, Model model){
        int result = boardService.deleteById(postId);
        model.addAttribute("result", result);
        return "board/deleteOk";
    }



    // 검색창
    @GetMapping("/search")
    public String getSerchList(String keyword,Integer page, Model model, String category){
        boardService.serchByList(keyword,page,model,category);
        System.out.println("작동확인"+ keyword+ page+ model+category);
        return "board/list";

    }


}   // end Controller
