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
public class Classifications {
    private Map<String, String> segment;
    private Map<String, String> genre;
    private Map<String, String> subGenre;
}
