// HotelDetail.java
package com.example.demo.domain.airandhotel;

import com.google.maps.model.Geometry;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDetail {
    private String placeId;
    private String name;
    private String formattedAddress;
    private double rating;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private String phoneNumber;
    private URL websiteUri;
    private String internationalPhoneNumber;
    private int priceLevel;
    private int userRatingCount;
    private List<URL> photos;
    private List<PlaceDetails.Review> review;


    public static HotelDetail fromPlacesSearchResult(PlacesSearchResult placesSearchResult) {
        return HotelDetail.builder()
                .name(placesSearchResult.name)
                .formattedAddress(placesSearchResult.formattedAddress)
                .rating(placesSearchResult.rating)
                .imageUrl(extractImageUrl(placesSearchResult))
                .latitude(getLocationValue(placesSearchResult.geometry, "lat"))
                .longitude(getLocationValue(placesSearchResult.geometry, "lng"))
                .placeId(placesSearchResult.placeId)
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

    private static double getLocationValue(Geometry geometry, String locationType) {
        return (geometry != null && geometry.location != null) ?
                (locationType.equals("lat") ? geometry.location.lat : geometry.location.lng)
                : 0.0;
    }

    public static HotelDetail fromPlaceDetails(PlaceDetails placeDetails) {
        return HotelDetail.builder()
                .placeId(placeDetails.placeId)
                .name(placeDetails.name)
                .formattedAddress(placeDetails.formattedAddress)
                .rating(placeDetails.rating)
                .imageUrl(extractImageUrl(placeDetails))
                .latitude(placeDetails.geometry.location.lat)
                .longitude(placeDetails.geometry.location.lng)
                .phoneNumber(placeDetails.formattedPhoneNumber)
                .websiteUri(placeDetails.website)
                .internationalPhoneNumber(placeDetails.internationalPhoneNumber)
                .priceLevel(placeDetails.priceLevel != null ? placeDetails.priceLevel.ordinal() : 0)
                .userRatingCount(placeDetails.userRatingsTotal)
                .photos(extractPhotos(placeDetails))
                .review(extractReviews(placeDetails))
                .build();
    }

    private static String extractImageUrl(PlaceDetails placeDetails) {
        String photoReference = (placeDetails.photos != null && placeDetails.photos.length > 0) ?
                placeDetails.photos[0].photoReference : null;

        return (photoReference != null) ?
                "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReference + "&key=AIzaSyAahD6lK89dPJgZkGSPY_koXo81g70jry8" :
                null;
    }

    private static List<URL> extractPhotos(PlaceDetails placeDetails) {
        List<URL> photoUrls = new ArrayList<>();
        if (placeDetails.photos != null) {
            for (Photo photo : placeDetails.photos) {
                String imageUrl = getImageUrl(photo);
                try {
                    URL url = new URL(imageUrl);
                    photoUrls.add(url);
                } catch (MalformedURLException e) {
                    // URL 생성 중에 오류가 발생할 경우 예외 처리를 수행합니다.
                    e.printStackTrace();
                }
            }
        }
        return photoUrls;
    }

    private static String getImageUrl(Photo photo) {
        return "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photo.photoReference + "&key=AIzaSyAahD6lK89dPJgZkGSPY_koXo81g70jry8";
    }

    private static List<PlaceDetails.Review> extractReviews(PlaceDetails placeDetails) {
        List<PlaceDetails.Review> reviews = new ArrayList<>();
        if (placeDetails.reviews != null) {
            reviews.addAll(Arrays.asList(placeDetails.reviews));
        }
        return reviews;
    }


}
