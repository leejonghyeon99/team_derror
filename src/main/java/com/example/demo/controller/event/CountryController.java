package com.example.demo.controller.event;

import com.example.demo.domain.event.ClassificationName;
import com.example.demo.domain.event.CountryCode;
import com.example.demo.domain.event.CountryInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


/**
 * @author 종선
 */

@Controller
@RequestMapping("/countryinfo")
public class CountryController {
    public static final String API_BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*&size=21";
    //public static final String API_BASE_URLE = "https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&id=G5v0Z9Yc3DYBk&locale=*&countryCode=US";

    public static final String API_BASE_URLA = "https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*";

    public CountryController() {
        System.out.println("CountryController() 생성");
    }

    @GetMapping("/search")
    public void search(Model model) {

        Map<String, String> codeMap = Arrays.stream(CountryCode.values()).collect(
                HashMap::new,
                (map, countryCode) -> map.put(countryCode.getCode(), countryCode.getValue()),
                HashMap::putAll
        );
        Map<String, String> classMap = Arrays.stream(ClassificationName.values()).collect(
                HashMap::new,
                (map, ClassificationName) -> map.put(ClassificationName.getClassificationName(), ClassificationName.getValue()),
                HashMap::putAll
        );
        model.addAttribute("codes", codeMap);
        model.addAttribute("classifications", classMap);
    }; // end search

    //https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*&countryCode=US


    @GetMapping("/list")
    @ResponseBody
    public CountryInfo list(@RequestParam String countryCode, @RequestParam(defaultValue = "1") Integer page, @RequestParam String classificationName) {
        String apiUrl = API_BASE_URL     + "&countryCode=" + countryCode + "&page=" + page + "&classificationName=" + classificationName;
        System.out.println(apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        CountryInfo countryInfo = restTemplate.getForObject(apiUrl, CountryInfo.class);

        countryInfo.getEmbedded().getEvents().forEach(events -> events.getId());
        System.out.println(countryInfo.getPage().getTotalPages());
        System.out.println(countryInfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getCity().getName());
        System.out.println(countryInfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getAddress().get("line1"));
        System.out.println(countryInfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getUpcomingEvents().get("_total"));

//        countryInfo.getEmbedded().getEvents().stream().reduce( (e,v) -> {
//
//            return null;
//        });

        return countryInfo;
    } // end CountryInfo

    @GetMapping("/info")
    public void info(@RequestParam String eventId, Model model) {
        String apiUrl = API_BASE_URLA + "&id=" + eventId;
        System.out.println(apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        CountryInfo countryInfo = restTemplate.getForObject(apiUrl, CountryInfo.class);


        model.addAttribute("info", countryInfo.getEmbedded().getEvents().get(0));
    }


} // end countryController