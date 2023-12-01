package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/user/detail/**").authenticated()
//                        .requestMatchers("/board/write/**", "/board/update/**", "/board/delete/**").hasAnyRole("MEMBER", "ADMIN")
                                .anyRequest().permitAll()
                )

                .formLogin(form -> form
                                .loginPage("/user/login")
                                .loginProcessingUrl("/user/login")

                                .defaultSuccessUrl("/")


//                        .successHandler(new CustomLoginSuccessHandler("/home"))
//
//                        .failureHandler(new CustomLoginFailureHandler())
                )
                .build();
    }
}
