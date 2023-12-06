package com.example.demo.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KakaoProfile {

    public Long id;
    @JsonProperty("connected_at")
    public String connectedAt;
    public Properties properties;
    @JsonProperty("kakao_account")
    public KakaoAccount kakaoAccount;

    @Data
    public class KakaoAccount {
        @JsonProperty("profile_nickname_needs_agreement")
        public Boolean profileNicknameNeedsAgreement;
        public Profile profile;

        @Data
        public class Profile {
            public String nickname;
        }
    }

    @Data
    public class Properties {
        public String nickname;
    }
}
