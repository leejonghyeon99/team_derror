package com.example.demo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {

    private Long id;
    private Long memberId;
    private Long masterId;
    private String title;
    private LocalDateTime createDate;
    private String national;
    private String IATA;
}
