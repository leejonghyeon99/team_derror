package com.example.demo.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;



public class FailureHandler implements AuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_FORWARD_URL = "/user/loginError";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errmsg = null;

        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errmsg = "아이디나 비밀번호가 맞지 않습니다.";
        }

        else if(exception instanceof DisabledException) {
            errmsg = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
        }

        else if(exception instanceof CredentialsExpiredException) {
            errmsg = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        }
        else {
            errmsg = "알수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
        }

        request.setAttribute("errmsg", errmsg);
        request.setAttribute("username", request.getParameter("username"));


        request.getRequestDispatcher(DEFAULT_FAILURE_FORWARD_URL).forward(request, response);  // forward!
    }
}
