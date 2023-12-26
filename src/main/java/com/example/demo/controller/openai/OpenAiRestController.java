package com.example.demo.controller.openai;

import com.example.demo.domain.openai.Sight;
import com.example.demo.service.openai.GptService;
import com.example.demo.service.openai.GptServiceImpl;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 이종현
 */
@RestController
@RequestMapping("/openai/api")
public class OpenAiRestController {

    private GptService gptService;

    @Autowired
    public OpenAiRestController(GptService gptService) {
        this.gptService = gptService;
    }


    @GetMapping("/chat")
    public ResponseEntity<List<ChatCompletionChoice>> chatResult(String prompt){
        System.out.println(prompt);

        return new ResponseEntity<>(gptService.chat(prompt),HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<Sight> recommend(
            String prompt
    ){
        System.out.println(prompt);
        return new ResponseEntity<>(gptService.getSightJson(prompt),HttpStatus.OK);
    }

    @PostMapping("/saveImages")
    public ResponseEntity<String> saveImage(@RequestBody List<String> imgUrl){
        System.out.println(imgUrl);
        gptService.saveImagesFromUrls(imgUrl);
        return new ResponseEntity<>("저장",HttpStatus.OK);
    }



}
