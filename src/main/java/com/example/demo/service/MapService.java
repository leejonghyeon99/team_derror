package com.example.demo.service;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    public String apiKey = "AIzaSyAahD6lK89dPJgZkGSPY_koXo81g70jry8";

    public String findHotels(String place) {
        try {
            GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, place + " hotels")
                    .type(PlaceType.LODGING)
                    .await();

            StringBuilder result = new StringBuilder();

            if (response.results.length > 0) {
                for (int i = 0; i < response.results.length; i++) {
                    result.append((i + 1) + ". " + response.results[i].name + "<br>");
                }
            } else {
                result.append("No hotels found in the specified location.");
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while searching for hotels.";
        }
    }
}
