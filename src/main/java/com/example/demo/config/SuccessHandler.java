package com.example.demo.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public SuccessHandler(String defaultTargetUrl){
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        PrincipalDetails userDetails = (PrincipalDetails)authentication.getPrincipal();

        List<String> authNames = new ArrayList<>();
        authentication.getAuthorities().forEach(authority -> {
            authNames.add(authority.getAuthority());
        });

        LocalDateTime loginTime = LocalDateTime.now();

        request.getSession().setAttribute("loginTime", loginTime);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
