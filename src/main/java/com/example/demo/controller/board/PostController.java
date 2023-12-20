package com.example.demo.controller.board;

import com.example.demo.domain.board.Post;
import com.example.demo.domain.board.PostPage;
import com.example.demo.domain.board.U;
import com.example.demo.service.board.CommentService;
import com.example.demo.service.post.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/board")
public class PostController {


    private PostService postService;
    private CommentService commentService;

    @Autowired
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


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

        model.addAttribute("result", postService.write(post, files));
        return "board/writeOk";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id){
        model.addAttribute("post",postService.detail(id));
        model.addAttribute("commentCnt", commentService.commentCntByPostId(id));
        model.addAttribute("parentComment",commentService.listbyParents(id));

        return "board/detail";
    }


    @GetMapping("/{category}")
    public void list(
            Integer page,
            Integer pageRows,
            @RequestParam(defaultValue = "id") String sort,
            @PathVariable String category,
            Model model
    ){
        U.getSession().setAttribute("pageRows", pageRows);
        postService.list(page,category,sort,model);

    }

    @GetMapping("/{category}/search")
    public String searchByKeyword(
            String keyword,
            Integer page,
            Integer pageRows,
            @RequestParam(defaultValue = "id") String sort,
            @PathVariable String category,
            Model model
    ){
        U.getSession().setAttribute("pageRows",pageRows);
        postService.findListByKeyWord(keyword,page,model,category,sort);
        return "board/"+category;
    }

    // 수정페이지
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        Post post = postService.findById(id);
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


        model.addAttribute("result", postService.update(post, files, delfile));
        return "board/updateOk";
    }

    // 삭제
    @PostMapping("/delete")
    public String deleteOk(Long postId, Model model){
        int result = postService.deleteById(postId);
        model.addAttribute("result", result);
        return "board/deleteOk";
    }
}
