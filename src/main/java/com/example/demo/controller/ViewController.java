package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 이종현
 */
@Controller
public class ViewController {

    @RequestMapping("/")
    public String main(){return "index";}

    @RequestMapping("/home")
    public String home(){return "home";}

    @RequestMapping("/calendar")
    public String calendar(){return "calendar";}

}
