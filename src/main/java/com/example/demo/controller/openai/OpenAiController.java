package com.example.demo.controller.openai;

import com.example.demo.service.openai.GptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 이종현
 */
@RestController
@RequestMapping("/openai")
public class OpenAiController {

    @Autowired
    GptService service;

    @GetMapping("/img")
    public ResponseEntity<String> test(){
        service.test3();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }



}
