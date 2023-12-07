package com.example.demo.controller.user;

import com.example.demo.domain.user.Member;
import com.example.demo.domain.user.MemberValidator;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    private MemberValidator memberValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(memberValidator);
    }

    @Autowired
    public UserController(UserService userService, MemberValidator memberValidator) {
        this.userService = userService;
        this.memberValidator = memberValidator;
    }

    @RequestMapping("/auth")
    @ResponseBody
    public Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

//    @RequestMapping("/home")
//    public void home(Model model){}

    @GetMapping("/login")
    public void login(Model model){}

    @PostMapping("/loginError")
    public String loginError(){
        return "user/login";
    }

    @GetMapping("/signup")
    public void signup(){}



    @PostMapping("/signup")
    public String signupOk(@Valid Member member
            , BindingResult result
            , Model model
            , RedirectAttributes redirectAttrs
    ){

        if(result.hasErrors()){
            redirectAttrs.addFlashAttribute("username", member.getUsername());
            redirectAttrs.addFlashAttribute("password", member.getPassword());
            redirectAttrs.addFlashAttribute("name", member.getName());
            redirectAttrs.addFlashAttribute("email", member.getEmail());
            redirectAttrs.addFlashAttribute("age", member.getAge());
            redirectAttrs.addFlashAttribute("phone", member.getPhone());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList) {
                redirectAttrs.addFlashAttribute("error", err.getCode());
                break;
            }

            return "redirect:/user/signup";
        }

        String page = "/user/signupOk";
        int cnt = userService.signup(member);
        model.addAttribute("result", cnt);
        return page;
    }



} // end Controller












