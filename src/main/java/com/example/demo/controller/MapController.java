package com.example.demo.controller;

import com.example.demo.domain.HotelDetail;
import com.example.demo.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    @RequestMapping("/service/hotel")
    public ResponseEntity<List<HotelDetail>> search(@RequestParam(required = false, defaultValue = "DefaultLocation") String city) {
        List<HotelDetail> hotelResults = mapService.findHotels(city);
        return new ResponseEntity<>(hotelResults, HttpStatus.OK);
    }
}
