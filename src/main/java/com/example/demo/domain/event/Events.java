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

    private String id;  // 해당 이벤트의 아이디
    private String name; // 이벤트 제목
    private String type; // 유형
    private List<Images> images;
    @JsonProperty("_embedded")
    private Embed embed;
//    private String timezone; // 시간대
//    private String country; // 국가이름
//    private String address; // 주소
//    private String location; // 경도, 위도 구글맵에 위치 띄우기
//    private int upcomingEvents; // 다가오는 이벤트 개수
}