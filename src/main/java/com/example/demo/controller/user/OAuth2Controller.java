//package com.example.demo.controller.user;
//
//import com.example.demo.domain.user.KakaoProfile;
//import com.example.demo.domain.user.Member;
//import com.example.demo.service.UserService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.client.RestTemplate;
//
//@Controller
//@RequestMapping("/oauth2")
//public class OAuth2Controller {
//    // kakao 로그인
//    @Value("${app.oauth2.kakao.client-id}")
//    private String kakaoClientId;
//    @Value("${app.oauth2.kakao.redirect-uri}")
//    private String kakaoRedirectUri;
//    @Value("${app.oauth2.kakao.token-uri}")
//    private String kakaoTokenUri;
//    @Value("${app.oauth2.kakao.user-info-uri}")
//    private String kakaoUserInfoUri;
//    @Value("${app.oauth2.password}")
//    private String oauth2Password;
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
////    public  String kakaoCallBack(String code){
////        KakaoProfile kakaoProfile =kakaoMemberInfo(token.getAccess_token());
////    }
//
//    private Member
//    signupKakao(KakaoProfile kakaoProfile) {
//        // 새로이 가입시킬 username 을 생성 (unique 해야 한다!)
//        String provider = "KAKAO";
//        String provider_id = "" + kakaoProfile.getId();
//        String username = provider + "_" + provider_id;
//        String name = kakaoProfile.getKakaoAccount().getProfile().getNickname();
//        String password = oauth2Password;
//
//        System.out.println("""
//               [카카오 인증 회원 정보]
//                 username: %s
//                 name: %s
//                 password: %s
//                 provider: %s
//                 providerId: %s
//               """.formatted(username, name, password, provider, provider_id));
//
//        // 회원 가입 진행하기 전에
//        // 이미 가입한 회원인지, 혹은 비가입자인지 체크하여야 한다
//        Member member = userService.findUsername(username);
//        if(member == null){   // 미가입자 인 경우 회원 가입 진행
//            Member newMember = Member.builder()
//                    .username(username)
//                    .name(name)
//                    .password(password)
//                    .provider(provider)
//                    .provider_id(provider_id)
//                    .build();
//
//            int cnt = userService.signup(newMember);  // 회원 가입!
//            if(cnt > 0){
//                System.out.println("[Kakao 인증 회원 가입 성공]");
//                member = userService.findUsername(username);  // 다시 읽어오기,  regDate 정보
//            } else {
//                System.out.println("[Kakao 인증 회원 가입 실패]");
//            }
//
//        } else {
//            System.out.println("[Kakao 인증. 이미 가입된 회원입니다]");
//        }
//
//        return member;
//    }
//    private KakaoProfile kakaoMemberInfo(String accessToken) {
//        RestTemplate rt = new RestTemplate();
//
//        // header  준비
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + accessToken);
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // body 는 필요없다.  위 header 만 담은 HttpEntity 생성
//        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
//                new HttpEntity<>(headers);
//
//        // 요청!
//        ResponseEntity<String> response = rt.exchange(
//                kakaoUserInfoUri,
//                HttpMethod.POST,
//                kakaoProfileRequest,
//                String.class
//        );
//        System.out.println("카카오 사용자 Profile 요청 응답: " + response);
//        System.out.println("카카오 사용자 Profile 응답 : " + response.getBody());
//
//        // 사용자 정보 JSON -> Java 로 받아내기
//        ObjectMapper mapper = new ObjectMapper();
//        KakaoProfile profile = null;
//        try {
//            profile = mapper.readValue(response.getBody(), KakaoProfile.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        // 확인
//        System.out.println("""
//                [카카오 회원정보]
//                 id: %s
//                 nickname: %s
//                """.formatted(profile.getId(), profile.getKakaoAccount().getProfile().getNickname()));
//
//        return profile;
//    }
//
//}
