package com.example.demo.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venues {
    private String id;
    private String name;
    private String type;
    private Map<String, String> city;
    private Map<String, String> address;
    private Map<String, Integer> upcomingEvents;
}
