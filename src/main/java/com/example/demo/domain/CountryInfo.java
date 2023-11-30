package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryInfo {
    private String name;
    private String type;
    private Long id;
    private String locale;
    private String timezone;
    private String city;
    private String state;
    private String country;
    private String address;
    private String location;
    private int upcomingEvents;

}
