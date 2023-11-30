package com.example.demo.controller.openai;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openai")
public class OpenAiController {


    @GetMapping("/img")
    public ResponseEntity<String> test(){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }



}
