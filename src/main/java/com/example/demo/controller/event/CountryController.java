package com.example.demo.controller.event;

import com.example.demo.domain.event.*;
import com.example.demo.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private final EventService eventService;

    @PostMapping("/save")
    public ResponseEntity<Events> registerEvent(@RequestBody Events events) {
        Events saveEvents = eventService.save(events);
        return new ResponseEntity<>(saveEvents, HttpStatus.CREATED);
    }

    public static final String API_BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*&size=21";

    public static final String API_BASE_URLA = "https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*";

    @Autowired
    public CountryController(EventService eventService) {
        this.eventService = eventService;
        System.out.println("CountryController() 생성");
    }


    @GetMapping("/search")
    public void search(Model model) {
        String apiUrl = API_BASE_URL;

        RestTemplate restTemplate = new RestTemplate();
        Countryinfo countryinfo = restTemplate.getForObject(apiUrl, Countryinfo.class);

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

        model.addAttribute("citys", countryinfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0));
        model.addAttribute("codes", codeMap);
        model.addAttribute("classifications", classMap);
    }; // end search

    //https://app.ticketmaster.com/discovery/v2/events?apikey=JVSEuY5G6jkq6i2eYx4EX53D0z5tZz64&locale=*&countryCode=US


    @GetMapping("/list")
    @ResponseBody
    public Countryinfo list(@RequestParam String countryCode, @RequestParam(defaultValue = "1") Integer page, @RequestParam String classificationName) {
        String apiUrl = API_BASE_URL + "&countryCode=" + countryCode + "&page=" + page + "&classificationName=" + classificationName;
        System.out.println(apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        Countryinfo countryinfo = restTemplate.getForObject(apiUrl, Countryinfo.class);

        countryinfo.getEmbedded().getEvents().forEach(events -> events.getId());
        System.out.println(countryinfo.getPage().getTotalPages());
        System.out.println(countryinfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getCity().getName());
        System.out.println(countryinfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getAddress().get("line1"));
        System.out.println(countryinfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0).getUpcomingEvents().get("_total"));

//        countryInfo.getEmbedded().getEvents().stream().reduce( (e,v) -> {
//
//            return null;
//        });

        return countryinfo;
    } // end Event

    @GetMapping("/info")
    public void info(@RequestParam String eventId, Model model) {
        String apiUrl = API_BASE_URLA + "&id=" + eventId;
        System.out.println(apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        Countryinfo countryinfo = restTemplate.getForObject(apiUrl, Countryinfo.class);

//        Events events = countryinfo.getEmbedded().getEvents().get(0);

//        model.addAttribute("etname", events.getName());
//        model.addAttribute("etimages", events.getImages());
//        model.addAttribute("etdates", events.getDates().getStart().getLocalDate());

        model.addAttribute("info", countryinfo.getEmbedded().getEvents().get(0));
        model.addAttribute("attr", countryinfo.getEmbedded().getEvents().get(0).getEmbed().getAttractions().get(0));
        model.addAttribute("loc", countryinfo.getEmbedded().getEvents().get(0).getEmbed().getVenues().get(0));

    }


} // end countryController