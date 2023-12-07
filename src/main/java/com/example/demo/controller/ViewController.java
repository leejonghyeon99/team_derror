package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 이종현
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/")
    public String main(){return "homepage";}
}
