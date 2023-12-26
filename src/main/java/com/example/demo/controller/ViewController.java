package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 이종현
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String main(){return "home";}

    @RequestMapping("/home")
    public String home(){return "home";}
}
