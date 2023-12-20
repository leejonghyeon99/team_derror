package com.example.demo.controller.user;

import com.example.demo.domain.board.Post;
import com.example.demo.domain.user.Member;
import com.example.demo.domain.user.MemberValidator;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private BoardService boardService;

    private MemberValidator memberValidator;
    @Autowired
    private  HttpSession httpSession;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(memberValidator);
    }

    @Autowired
    public UserController(UserService userService, BoardService boardService, MemberValidator memberValidator
                            ) {
        this.userService = userService;
        this.memberValidator = memberValidator;
        this.boardService = boardService;

    }

    //
    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @RequestMapping("/test")
    public void test(Model model) {
    }


    @GetMapping("/login")
    public void login(Model model) {
    }

    @PostMapping("/loginError")
    public String loginError() {
        return "user/login";
    }

    @GetMapping("/signup")
    public void signup() {
    }

    @GetMapping("/signout")
    public void signout() {
    }


    @PostMapping("/signup")
    public String signupOk(@Valid Member member
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ) {

        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("username", member.getUsername());
            redirectAttrs.addFlashAttribute("password", member.getPassword());
            redirectAttrs.addFlashAttribute("re_password", member.getRe_password());
            redirectAttrs.addFlashAttribute("name", member.getName());
            redirectAttrs.addFlashAttribute("email", member.getEmail());
            redirectAttrs.addFlashAttribute("age", member.getAge());
            redirectAttrs.addFlashAttribute("phone", member.getPhone());

            List<FieldError> errList = result.getFieldErrors();
            redirectAttrs.addFlashAttribute("errors", errList);
            for (int i = 0; i < errList.size(); i++) {

                redirectAttrs.addFlashAttribute("err" + errList.get(i).getField(), errList.get(i).getCode());
            }


            return "redirect:/user/signup";
        }

        String page = "/user/signupOk";
        int cnt = userService.signup(member);
        model.addAttribute("result", cnt);
        return page;
    }

    @GetMapping("/detail")
    public String detail(Principal principal, Model model) {
        String loginId = principal.getName();
        List<Post> list = boardService.findByUserName(loginId);
        Member member = userService.findUsername(loginId);
        model.addAttribute("member", member);
        model.addAttribute("list", list);


        return "user/detail";
    }

        @PostMapping("/signout")
        public String removeUser(Model model, HttpServletRequest request, HttpServletResponse response) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if (authentication != null && authentication.isAuthenticated()) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                Member member = userService.findUsername(userDetails.getUsername());

                if (member != null) {
                    int result = userService.removeById(member.getId());
                    new SecurityContextLogoutHandler().logout(request, response, authentication);
                    model.addAttribute("result", result);
                    return "/user/signout";
                }
            }
            return "/user/login";
        }

}












