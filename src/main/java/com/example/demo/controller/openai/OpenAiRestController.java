package com.example.demo.controller.openai;

import com.example.demo.domain.openai.Sight;
import com.example.demo.service.openai.GptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 이종현
 */
@RestController
@RequestMapping("/openai/api")
public class OpenAiRestController {

    @Autowired
    GptService service;

    @GetMapping("/recommendation")
    public ResponseEntity<Sight> search(@RequestParam String keyword){
        System.out.println(keyword);
        return new ResponseEntity<>(service.searchKeyWord(keyword), HttpStatus.OK);
    }




}
