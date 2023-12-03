package com.example.demo.controller.event;

import com.example.demo.domain.event.CountryInfo;
import com.example.demo.domain.event.Embedded;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 종선
 */

@Controller
@RequestMapping("/countryinfo")
public class CountryController {
    public static final String API_BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*";

    public CountryController() {
        System.out.println("CountryController() 생성");
    }
    @GetMapping("/search")
    public void search(){};

    //https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*&countryCode=US

    @GetMapping("/list")
    @ResponseBody
    public CountryInfo list(@RequestParam String countryCode) {
        String apiUrl = API_BASE_URL + "&countryCode=" + countryCode;
        System.out.println(apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        CountryInfo countryInfo = restTemplate.getForObject(apiUrl, CountryInfo.class);
        countryInfo.getEmbedded().getEvents().forEach(events -> events.getId());
        System.out.println(countryInfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getCity().get("name"));
        System.out.println(countryInfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getAddress().get("line1"));
        System.out.println(countryInfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getUpcomingEvents().get("_total"));
        return countryInfo;
    }
}