package com.example.demo.domain.openai;

import lombok.Data;

import java.util.List;

@Data
public class Warn {
    private String Police;
    private String emergency;
    private List<Danger> danger;
}
