package com.example.demo.controller.airandhotel;

import com.example.demo.service.airandhotel.AirPortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airandhotel")
public class AirAndHotelController {

    private AirPortService airPortService;

    @Autowired
    public AirAndHotelController(AirPortService airPortService) {
        this.airPortService = airPortService;
    }

    @RequestMapping("/airport")
    public String airport() {
        return "airandhotel/airport";
    }

    @RequestMapping("/hotel")
    public String hotel(){return "airandhotel/hotel";}


}
