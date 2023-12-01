// HotelDetail.java
package com.example.demo.domain.airandhotel;

import com.google.maps.model.PlacesSearchResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDetail {
    private String name;
    private String formattedAddress;
    private double rating;
    private String imageUrl;
    private double latitude;
    private double longitude;

    public static HotelDetail fromPlacesSearchResult(PlacesSearchResult placesSearchResult) {
        String imageUrl = extractImageUrl(placesSearchResult);

        return HotelDetail.builder()
                .name(placesSearchResult.name)
                .formattedAddress(placesSearchResult.formattedAddress)
                .rating(placesSearchResult.rating)
                .imageUrl(imageUrl)
                .build();
    }

    private static String extractImageUrl(PlacesSearchResult placesSearchResult) {
        if (placesSearchResult.photos != null && placesSearchResult.photos.length > 0) {
            return getImageUrl(placesSearchResult.photos[0].photoReference);
        }
        return null;
    }

    private static String getImageUrl(String photoReference) {
        return "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReference + "&key=AIzaSyAahD6lK89dPJgZkGSPY_koXo81g70jry8";
    }

}
