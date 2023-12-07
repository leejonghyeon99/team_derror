package com.example.demo.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Events {

    private String id;
    private String name;
    private String type;
    private List<Images> images;
    @JsonProperty("_embedded")
    private Embed embed;
    private Dates dates;

}