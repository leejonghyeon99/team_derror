package com.example.demo.controller.openai;

import com.example.demo.domain.openai.Sight;
import com.example.demo.service.openai.GptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 이종현
 */
@RestController
@RequestMapping("/openai/api")
public class OpenAiRestController {

    @Autowired
    GptService service;

    @GetMapping("/recommendation")
    public ResponseEntity<Sight> search(String prompt){
        System.out.println(prompt);
        return new ResponseEntity<>(service.searchKeyWord(prompt), HttpStatus.OK);
    }

    @GetMapping("/chat")
    public ResponseEntity<List<ChatCompletionChoice>> chatResult(String prompt){
        System.out.println(prompt);

        return new ResponseEntity<>(service.chat(prompt),HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<Sight> test(
            String prompt
    ){
        System.out.println(prompt);
        return new ResponseEntity<>(service.getSightJson(prompt),HttpStatus.OK);
    }



}
