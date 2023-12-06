package com.example.demo.controller.airandhotel;

import com.example.demo.controller.board.BoardController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/airandhotel")
public class MainController {

    @RequestMapping("/airport")
    public String Search(){
       return "airandhotel/airport";
    }
  
    @RequestMapping("/hotel")
    public String Index(){return "airandhotel/hotel";}
}
