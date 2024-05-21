package com.ssafy.xmagazine.oauth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.xmagazine.domain.user.UserService;

@Controller
public class OAuthController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/oauth2/callback/google")
    public ResponseEntity<?> handleGoogleOAuth2Callback(@RequestParam String code) {
        // 인증 코드를 사용하여 토큰 요청
        String accessToken = authService.getAccessTokenFromGoogle(code);
        // 토큰을 사용하여 사용자 정보 가져오기
        OAuth2UserInfo userInfo = authService.getGoogleUserInfo(accessToken);
        // 필요한 로직 처리 (예: 사용자 정보 저장)
        userService.processOAuthPostLogin(userInfo);
        // 클라이언트에 결과 전달
        return ResponseEntity.ok().body(userInfo);
    }
}