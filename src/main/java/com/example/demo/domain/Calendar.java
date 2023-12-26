package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {

    private Long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String color;
    private boolean allDay;
    private String memo;
    private Long memberId;

    // Getter methods for formatted dates
    public String getStart() {
        return startDate != null ? startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }

    public String getEnd() {
        return endDate != null ? endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }

}
