package com.example.demo.controller.openai;

import com.example.demo.domain.board.U;
import com.example.demo.domain.openai.Recommendations;
import com.example.demo.domain.openai.Sight;
import com.example.demo.service.openai.GptService;
import com.google.gson.JsonObject;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/openai")
public class OpenAiController {

    private GptService gptService;

    @Autowired
    public OpenAiController(GptService gptService) {
        this.gptService = gptService;
    }



    @GetMapping("/main")
    public void main(Model model){
        System.out.println("로그인한 사람: "+U.getLoggedUser().getId());
        System.out.println(gptService.settingSight(U.getLoggedUser().getId()));
        List<Recommendations> recommendations = gptService.settingSight(U.getLoggedUser().getId());
        if (recommendations !=null){
            model.addAttribute("recommend",gptService.settingSight(U.getLoggedUser().getId()));
        }
    }

}
