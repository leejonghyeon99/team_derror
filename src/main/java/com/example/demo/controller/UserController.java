package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public void login(Model model){}

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
            redirectAttrs.addFlashAttribute("name", member.getName());
            redirectAttrs.addFlashAttribute("email", member.getEmail());
            redirectAttrs.addFlashAttribute("age", member.getAge());
            redirectAttrs.addFlashAttribute("phone", member.getPhone());
            redirectAttrs.addFlashAttribute("password", member.getPassword());

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












