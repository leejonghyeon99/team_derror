package com.example.demo.controller;

import com.example.demo.service.CountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/countryinfo")
public class CountryInfoController {

//    @Autowired
//    private CountryInfoService countryInfoService;

    public CountryInfoController() {
        System.out.println("CountryInfoController() 생성");
    }

    @GetMapping("/loadinfo")
    public void loadinfo(){}

}
