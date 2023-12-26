package com.example.demo.domain.openai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendations {

    private Long id;
    private Long memberId;
    private String question;
    private String national;
    private String countryCode;
    private String city;
    private String place;
    private String address;
    private String longitude;
    private String latitude;
    private String detail;
    private String language;
    private List<OpenAiImg> images;
    private LocalDateTime regDate;
}
