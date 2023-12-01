package com.example.demo.controller.airandhotel;

import com.example.demo.domain.airandhotel.HotelDetail;
import com.example.demo.service.airandhotel.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class MapController {

    @Autowired
    private MapService mapService;

    @RequestMapping("/service")
    public ResponseEntity<List<HotelDetail>> search(@RequestParam(required = false, defaultValue = "DefaultLocation") String city) {
        List<HotelDetail> hotelResults = mapService.findHotels(city);
        return new ResponseEntity<>(hotelResults, HttpStatus.OK);
    }
}
