// MapService.java
package com.example.demo.service.airandhotel;

import com.example.demo.domain.airandhotel.HotelDetail;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapService {

    private String apiKey = "AIzaSyAahD6lK89dPJgZkGSPY_koXo81g70jry8";

    public List<HotelDetail> findHotels(String place) {
        try {
            GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, place + " hotels")
                    .type(PlaceType.LODGING)
                    .language("ko")
                    .await();

            if (response.results != null && response.results.length > 0) {
                return convertToHotelDetails(response.results);
            } else {
                return new ArrayList<>();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // 에러 시 빈 리스트 반환
        }
    }


    private List<HotelDetail> convertToHotelDetails(PlacesSearchResult[] placesSearchResults) {
        List<HotelDetail> hotelDetails = new ArrayList<>();

        for (PlacesSearchResult placesSearchResult : placesSearchResults) {
            HotelDetail hotelDetail = HotelDetail.fromPlacesSearchResult(placesSearchResult);

            // 위치 정보를 가져와 HotelDetail에 설정
            if (placesSearchResult.geometry != null && placesSearchResult.geometry.location != null) {
                hotelDetail.setLatitude(placesSearchResult.geometry.location.lat);
                hotelDetail.setLongitude(placesSearchResult.geometry.location.lng);
            }

            hotelDetails.add(hotelDetail);
        }



        return hotelDetails;
    }

    // 다른 메서드들도 필요에 따라 추가 가능
}
