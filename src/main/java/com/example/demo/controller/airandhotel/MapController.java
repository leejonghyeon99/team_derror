package com.example.demo.controller.airandhotel;

import com.example.demo.domain.airandhotel.HotelDetail;
import com.example.demo.service.airandhotel.HotelDetailService;
import com.example.demo.service.airandhotel.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/hotel")
public class MapController {

    @Autowired
    private MapService mapService;

    @Autowired
    private HotelDetailService hotelDetailService;

    @RequestMapping("/service")
    public ResponseEntity<List<HotelDetail>> search(@RequestParam(required = false, defaultValue = "DefaultLocation") String query) {
        List<HotelDetail> hotelResults = mapService.findHotels(query);
        return new ResponseEntity<>(hotelResults, HttpStatus.OK);
    }

    @RequestMapping("/service/{placeId}")
    public ResponseEntity<HotelDetail> getHotelDetail(@PathVariable String placeId) {
        HotelDetail hotelDetail = hotelDetailService.getHotelDetail(placeId);
        if (hotelDetail != null) {
            return new ResponseEntity<>(hotelDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

