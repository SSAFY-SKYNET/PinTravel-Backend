package com.ssafy.xmagazine.oauth;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.mapper.UserMapper;
import com.ssafy.xmagazine.util.JWTUtil;

import jakarta.servlet.http.Cookie;
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

        // JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(userId);
        String refreshToken = jwtUtil.createRefreshToken(userId);

        // URL에 토큰을 포함하여 리디렉션

        // 로컬 연결
        // String redirectUrl = "http://localhost:5173/loginSuccess";

        // 배포 연결
        String redirectUrl = "http://pintravel.store/loginSuccess";

        redirectUrl += "?accessToken=" + URLEncoder.encode(accessToken, "UTF-8");
        redirectUrl += "&refreshToken=" + URLEncoder.encode(refreshToken, "UTF-8");

        response.sendRedirect(redirectUrl);
    }
}