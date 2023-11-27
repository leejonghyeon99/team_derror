package com.example.demo.controller;

import com.example.demo.domain.Location;
import com.example.demo.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/service/api")
public class ServiceApiController {

    @RequestMapping("/code")
    public String code (String type){

        String baseUri = "https://apis.data.go.kr/B551177/StatusOfSrvDestinations/getServiceDestinationInfo?" +
                "serviceKey=CT7mffdJdearTHAc%2FEi2x1GUgdncDCCVUNVqAzxE3evRp1RJf9y7qXk9%2F8hcg%2Bb6cplme%2FuY8a2xm0O3u0gxsg%3D%3D" +
                "&type=json";


        URI uri = UriComponentsBuilder
                .fromUriString(baseUri)
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        return result.getBody();
    } // end code

    @RequestMapping("/airport")
    public String airport(String airport){

        String baseUri = "http://apis.data.go.kr/B551177/PaxFltSched/getPaxFltSchedArrivals";
        String apiKey = "CT7mffdJdearTHAc%2FEi2x1GUgdncDCCVUNVqAzxE3evRp1RJf9y7qXk9%2F8hcg%2Bb6cplme%2FuY8a2xm0O3u0gxsg%3D%3D";

        URI uri = UriComponentsBuilder
                .fromUriString(baseUri)
                .queryParam("serviceKey", apiKey)
                .queryParam("numOfRows", 200)
                .queryParam("lang", "K")
                .queryParam("airport", airport)
                .queryParam("type", "json")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        return result.getBody();

    } //end airport





} // end controller
