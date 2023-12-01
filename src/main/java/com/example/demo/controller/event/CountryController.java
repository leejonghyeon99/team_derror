package com.example.demo.controller.event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/countryinfo")
public class CountryController {

    public static final String API_BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*";


    public CountryController() {
        System.out.println("CountryInfoController() 생성");
    }
//https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*&countryCode=US
    @GetMapping("/listSearch")
    public String listSearch(@RequestParam String countryCode){
        String apiUrl = API_BASE_URL + "&countryCode=" + countryCode;
        System.out.println(apiUrl);
        RestTemplate restTemplate = new RestTemplate();
        String apiResponse = restTemplate.getForObject(apiUrl, String.class);


        return apiResponse;
    }

}
