package com.example.demo.controller.user;

import com.example.demo.domain.user.Plan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 이종현
 */
@Controller
@RequestMapping("/plan")
public class PlanController {

    @GetMapping("/myplan")
    public void plan(Model model){

        Plan plan = Plan.builder()
                .id(1L)
                .memberId(1L)
                .masterId(1L)
                .title("testTitle")
                .national("미국")
                .createDate(LocalDateTime.now())
                .IATA("WTZ")
                .build();

        Plan plan2 = Plan.builder()
                .id(2L)
                .memberId(1L)
                .masterId(2L)
                .title("testTitle2")
                .national("독일")
                .createDate(LocalDateTime.now())
                .IATA("ABC")
                .build();

        List<Plan> list = new ArrayList<>();
        list.add(plan);
        list.add(plan2);
        model.addAttribute("planList",list);
    }

    @GetMapping("/mypage")
    public String mypage(){return "user/mypage";}
}
