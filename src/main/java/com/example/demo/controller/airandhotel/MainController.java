package com.example.demo.controller.airandhotel;

import com.example.demo.domain.airandhotel.Items;
import com.example.demo.service.airandhotel.AirPortService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/airandhotel")
public class MainController {

    private AirPortService airPortService;

    @Autowired
    public MainController(AirPortService airPortService) {
        this.airPortService = airPortService;
    }

    @RequestMapping("/airport")
    public String airport() {
        return "airandhotel/airport";
    }

    @RequestMapping("/hotel")
    public String hotel(){return "airandhotel/hotel";}


}
