package com.example.demo.controller.openai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/openai")
public class OpenAiController {

    @GetMapping("/main")
    public void main(){}
}
