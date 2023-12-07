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
        @JsonProperty("profile_image_needs_agreement")
        public Boolean profileImageNeedsAgreement;
        public Profile profile;
        @JsonProperty("has_email")
        public Boolean hasEmail;
        @JsonProperty("email_needs_agreement")
        public Boolean emailNeedsAgreement;
        @JsonProperty("has_birthday")
        public Boolean hasBirthday;
        @JsonProperty("birthday_needs_agreement")
        public Boolean birthdayNeedsAgreement;
        @JsonProperty("has_gender")
        public Boolean hasGender;
        @JsonProperty("gender_needs_agreement")
        public Boolean genderNeedsAgreement;
        @Data
        public class Profile {
            public String nickname;
            @JsonProperty("thumbnail_image_url")
            public String thumbnailImageUrl;
            @JsonProperty("profile_image_url")
            public String profileImageUrl;
            @JsonProperty("is_default_image")
            public Boolean isDefaultImage;
        }
    }

    @Data
    public class Properties {
        public String nickname;
        @JsonProperty("profile_image")
        public String profileImage;
        @JsonProperty("thumbnail_image")
        public String thumbnailImage;
    }
}
