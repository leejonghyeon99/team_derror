package com.example.demo.service.airandhotel;

import com.example.demo.domain.airandhotel.AirPort;
import com.example.demo.domain.airandhotel.Items;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class AirPortServiceImpl implements AirPortService {

    @Override
    public List<Items> airportInfo(String code) {
        String baseUri = "https://apis.data.go.kr/B551177/PaxFltSched/getPaxFltSchedArrivals";
        String apiKey = "CT7mffdJdearTHAc%2FEi2x1GUgdncDCCVUNVqAzxE3evRp1RJf9y7qXk9%2F8hcg%2Bb6cplme%2FuY8a2xm0O3u0gxsg%3D%3D";

        URI uri = UriComponentsBuilder
                .fromUriString(baseUri)
                .queryParam("serviceKey", apiKey)
                .queryParam("numOfRows", 200)
                .queryParam("lang", "K")
                .queryParam("airport", code)
                .queryParam("type", "json")
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AirPort> responseEntity = restTemplate.getForEntity(uri, AirPort.class);

        // 응답에서 AirPort 객체를 가져옴
        AirPort airPort = responseEntity.getBody();

        // AirPort 객체에서 Items 리스트를 가져옴
        List<Items> itemsList = airPort.getResponse().getBody().getItems();

        return itemsList;
    }
}
