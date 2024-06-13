package com.ssafy.xmagazine.oauth;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.mapper.UserMapper;
import com.ssafy.xmagazine.util.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email"); // 사용자 식별자를 얻습니다.
        int userId = userMapper.selectUserByEmail(email).getUserId();
        log.info("사용자 로그인 성공: {}", email);

        // JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(userId);
        String refreshToken = jwtUtil.createRefreshToken(userId);
        log.info("Access Token: {}", accessToken);
        log.info("Refresh Token: {}", refreshToken);

        // String redirectUrl = "http://pintravel.store/loginSuccess";
        String redirectUrl = "http://localhost:5173/loginSuccess";
        redirectUrl += "?accessToken=" + URLEncoder.encode(accessToken, "UTF-8");
        redirectUrl += "&refreshToken=" + URLEncoder.encode(refreshToken, "UTF-8");
        response.sendRedirect(redirectUrl);

        log.info("리디렉션 URL: {}", redirectUrl);
    }
}