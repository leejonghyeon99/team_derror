package com.example.demo.controller.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 종선
 */

@RestController
@RequestMapping("/countryinfo")
public class CountryController {

    public static final String API_BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*";

//    String serviceKey = "JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64";

    public CountryController() {
        System.out.println("CountryInfoController() 생성");
    }

    //https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*&countryCode=US
//    @GetMapping("/listSearch")
//    public String listSearch(@RequestParam String countryCode) {
//        String apiUrl = API_BASE_URL + "&countryCode=" + countryCode;
//        System.out.println(apiUrl);
//        RestTemplate restTemplate = new RestTemplate();
//        String apiResponse = restTemplate.getForObject(apiUrl, String.class);
//
//        return apiResponse;
//    }

    @RequestMapping("/listSearch")
    public String listSearch(String eventsId) {
        String baseUrl = "https://app.ticketmaster.com/discovery/v2/events";
        String apiKey = "JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*";

        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .pathSegment(eventsId)
                .queryParam("apiKey", apiKey)
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        return result.getBody();
    }

}