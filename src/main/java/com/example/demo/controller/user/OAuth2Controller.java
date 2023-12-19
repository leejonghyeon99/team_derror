package com.example.demo.controller.user;


import com.example.demo.domain.board.U;
import com.example.demo.domain.user.KakaoOAuthToken;
import com.example.demo.domain.user.KakaoProfile;
import com.example.demo.domain.user.Member;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;




@Controller
@RequestMapping("/oauth2")
public class OAuth2Controller {
    // kakao 로그인
    @Value("${app.oauth2.kakao.client-id}")
    private String kakaoClientId;
    @Value("${app.oauth2.kakao.redirect-uri}")
    private String kakaoRedirectUri;
    @Value("${app.oauth2.kakao.token-uri}")
    private String kakaoTokenUri;
    @Value("${app.oauth2.kakao.user-info-uri}")
    private String kakaoUserInfoUri;
    @Value("${app.oauth2.password}")
    private String oauth2Password;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/kakao/callback")
    public String kakaoCallBack(String code){

        System.out.println("\n<<카카오 인증 완료>>\ncode: " + code);
       KakaoOAuthToken token = kakaoAccessToken(code);

      KakaoProfile kakaoProfile = kakaoUserInfo(token.getAccess_token());

      Member kakaoMember = signupKakao(kakaoProfile);
      loginKakao(kakaoMember);

        return "redirect:/";
    }

    private void loginKakao(Member kakaoMember){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                kakaoMember.getUsername(),
                oauth2Password
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

        U.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        System.out.println("Kakao 인증 로그인 처리 완료");
    }

    private Member signupKakao(KakaoProfile kakaoProfile){

        String provider = "KAKAO";
        String provider_id = "" + kakaoProfile.getId();
        String username = provider + "_" + provider_id;
        String name = kakaoProfile.getKakaoAccount().getProfile().getNickname();
        String password = oauth2Password;
        Long age =20L;
        String phone = "010-4444-5555";
        String email = "imsen12@naver.com";
        Member member = userService.findUsername(username);

        System.out.println("""
               [카카오 인증 회원 정보]
                 username: %s
                 name: %s
                 age: %d
                 phone: %s
                 email: %s     
                 password: %s  
                 provider: %s
                 providerId: %s  
                      
               """.formatted(username, name,age,phone,email, password, provider, provider_id));

        if(member == null){
            Member newMember = Member.builder()
                    .username(username)
                    .name(name)
                    .age(Math.toIntExact(age))
                    .phone(phone)
                    .email(email)
                    .password(password)
                    .provider(provider)
                    .provider_id(provider_id)
                    .build();

            int cnt = userService.signup(newMember);
            if(cnt > 0){
                System.out.println("[Kakao 인증 회원 가입 성공]");
                member = userService.findUsername(username);
            } else {
                System.out.println("[Kakao 인증 회원 가입 실패]");
            }

        } else {
            System.out.println("[Kakao 인증. 이미 가입된 회원입니다]");
        }

        return member;
    }

    private KakaoProfile kakaoUserInfo(String accessToken){
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);


        ResponseEntity<String> response = rt.exchange(
                kakaoUserInfoUri,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );
        System.out.println("카카오 사용자 Profile 요청 응답: " + response);
        System.out.println("카카오 사용자 Profile 응답 : " + response.getBody());


        ObjectMapper mapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = mapper.readValue(response.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 확인
        System.out.println("""
                [카카오 회원정보]
                 id: %s
                 nickname: %s
                """.formatted(kakaoProfile.getId(), kakaoProfile.getKakaoAccount().getProfile().getNickname()));

        return kakaoProfile;

    }

    public KakaoOAuthToken kakaoAccessToken(String code){

        RestTemplate rt = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);


        ResponseEntity<String> response = rt.exchange(
                kakaoTokenUri,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        System.out.println("카카오 AccessToken 요청 응답: " + response);
        System.out.println("카카오 AccessToken 응답 body: " + response.getBody());


        ObjectMapper mapper = new ObjectMapper();
        KakaoOAuthToken token = null;


        try {
            token = mapper.readValue(response.getBody(), KakaoOAuthToken.class);

            System.out.println("카카오 AccessToken: " + token.getAccess_token());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return token;
    }
}
