package com.example.demo.config;

import org.springframework.boot.util.Instantiator;
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
                        // URL 과 접근권한 세팅(들)
                        // ↓ /board/detail/** URL로 들어오는 요청은 '인증'만 필요.
                        .requestMatchers("/board/detail/**").authenticated()
                        // ↓ "/board/write/**", "/board/update/**", "/board/delete/**" URL로 들어오는 요청은 '인증' 뿐 아니라 ROLE_MEMBER 나 ROLE_ADMIN 권한을 갖고 있어야 한다. ('인가')
                        .requestMatchers("/board/write/**", "/board/update/**", "/board/delete/**").hasAnyRole("USER", "ADMIN")
                        // ↓ 그 밖의 다른 요청은 모두 permit!
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/")
                .successHandler(new SuccessHandler("/home"))
                .failureHandler(new FailureHandler())
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .build();



    }
}
