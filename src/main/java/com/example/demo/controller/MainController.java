package com.example.demo.controller;

import com.example.demo.controller.board.BoardController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @RequestMapping("/search")
    public String Search(){
       return "Search";
    }

}
