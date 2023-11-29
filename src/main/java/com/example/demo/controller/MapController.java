package com.example.demo.controller;

import com.example.demo.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {

    @Autowired
    private MapService mapService;
    
    @RequestMapping("/hotel")
    public String hotel (@RequestParam(required = false, defaultValue = "DefaultLocation") String location, Model model) {
        String hotelResult =  mapService.findHotels(location);
        model.addAttribute("hotelResult", hotelResult);
        model.addAttribute("location", location);
        return "index";
    }

}
