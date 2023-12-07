package com.example.demo.domain.airandhotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Items {
    private String flightid;
    private String airline;
    private String st;
    private String firstdate;
    private String lastdate;
    private String airportcode;
    private String airport;
    private String season;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
}
