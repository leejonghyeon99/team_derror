package com.example.demo.config;

import com.example.demo.domain.user.Member;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${app.oauth2.password}")
    private String oauth2Password;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        System.out.println("""
                loadUser() 호출
                  ClientRegistration:%s
                  RegistrationId:%s
                  AccessToken:%s
                  OAuth2User Attributes:%s
                """.formatted(oAuth2UserRequest.getClientRegistration()
                , oAuth2UserRequest.getClientRegistration().getRegistrationId()
                , oAuth2UserRequest.getAccessToken().getTokenValue()
                , oAuth2User.getAttributes()
        ));
        String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo= switch (provider.toLowerCase()){
            case "google" -> new GoogleUserInfo(oAuth2User.getAttributes());
            default -> null;
        };


        String provider_id =oAuth2UserInfo.getProviderId();
        String username = provider + "_" + provider_id;
        String name = oAuth2UserInfo.getName();
        String password = passwordEncoder.encode(oauth2Password);
        Long age =30L;
        String phone = "010-1234-5678";
        String email = oAuth2UserInfo.getEmail();

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

        Member member = userService.findUsername(username);
        if(member == null){
            member=newMember;
            int num = userService.signup(member);
            if(num > 0){
                System.out.println("Google 회원가입성공");
                member = userService.findUsername(username);
            } else {
                System.out.println("Google 회원가입실패");
            }
        } else {
            System.out.println("Google 이미 가입된 회원입니다.");
        }

        PrincipalDetails principalDetails = new PrincipalDetails(member,oAuth2User.getAttributes());
        principalDetails.setUserService(userService);
        return principalDetails;
    }
}