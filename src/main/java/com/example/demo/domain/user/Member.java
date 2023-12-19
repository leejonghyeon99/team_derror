package com.example.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    private Long id;
    private Long auth_id;
    private String username;

    @JsonIgnore
    private String password;
    private LocalDateTime created_date;

    @ToString.Exclude
    @JsonIgnore
    private String re_password;
    private int age;
    private String phone;
    private String name;
    private String email;
    private String thumbnail_img;
    private String provider;
    private String provider_id ;





}
