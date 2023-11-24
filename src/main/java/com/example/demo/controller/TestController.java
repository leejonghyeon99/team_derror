package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/")
public class TestController {


    private static final String API_KEY = "8mYuSsx806fLeChycpFQRXyQufsEGsAB";
    private static final String BASE_URL = "https://app.ticketmaster.eu/mfxapi/v2/";


    @RequestMapping("/api")
    public String api(){

        try {
            String apiUrl = "https://app.ticketmaster.com/discovery/v2/events.json?size=1&apikey=8mYuSsx806fLeChycpFQRXyQufsEGsAB";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("HTTP request failed: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @RequestMapping("/api2")
    public String api2(){
        System.out.println("call api2");
        try {
            String apiUrl = "https://advertiser-site.com/api_implementation/hotel_data";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                System.out.println("HTTP request failed: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }




}
