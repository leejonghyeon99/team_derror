package com.example.demo.controller.airandhotel;

import com.example.demo.domain.airandhotel.AirPort;
import com.example.demo.domain.airandhotel.Items;
import com.example.demo.service.airandhotel.AirPortService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/air")
public class ServiceApiController {

    private AirPortService airPortService;

    @Autowired
    public ServiceApiController(AirPortService airPortService) {
        this.airPortService = airPortService;
    }

    @RequestMapping("/code")
    public String code (){

        String baseUri = "https://apis.data.go.kr/B551177/StatusOfSrvDestinations/getServiceDestinationInfo";
        String serviceKey = "CT7mffdJdearTHAc%2FEi2x1GUgdncDCCVUNVqAzxE3evRp1RJf9y7qXk9%2F8hcg%2Bb6cplme%2FuY8a2xm0O3u0gxsg%3D%3D";

        URI uri = UriComponentsBuilder
                .fromUriString(baseUri)
                .queryParam("serviceKey", serviceKey)
                .queryParam("type", "json")
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        return result.getBody();
    } // end code


    @PostMapping("/airport")
    public ResponseEntity<List<Items>> airport(@RequestBody Map<String, String> requestBody) {
        String code = requestBody.get("code");

        // code 값을 이용하여 작업 수행
        List<Items> airPortList = airPortService.airportInfo(code);

        return new ResponseEntity<>(airPortList, HttpStatus.OK);
    }


} // end controller
