package com.gati.hankki.oauth;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {

    private String name;
    private String email;
    private String picture;
    private String provider;
    private String providerId;

    // provider별 정보 파싱
    public static OAuthAttributes of(String provider, Map<String, Object> attributes) {
        if ("google".equals(provider)) {
            return ofGoogle(provider, attributes);
        } else if ("kakao".equals(provider)) {
            return ofKakao(provider, attributes);
        } else if ("naver".equals(provider)) {
            return ofNaver(provider, attributes);
        }
        throw new IllegalArgumentException("지원하지 않는 provider: " + provider);
    }

    private static OAuthAttributes ofGoogle(String provider, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .provider(provider)
                .providerId((String) attributes.get("sub"))
                .build();
    }

    private static OAuthAttributes ofKakao(String provider, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) profile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .provider(provider)
                .providerId(String.valueOf(attributes.get("id")))
                .build();
    }

    private static OAuthAttributes ofNaver(String provider, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .provider(provider)
                .providerId((String) response.get("id"))
                .build();
    }

}
