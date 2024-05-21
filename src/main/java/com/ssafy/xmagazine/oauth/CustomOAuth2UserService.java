package com.ssafy.xmagazine.oauth;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.domain.user.UserDto;
import com.ssafy.xmagazine.mapper.UserMapper;
import com.ssafy.xmagazine.util.JWTUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserMapper userMapper;
    private final HttpSession httpSession;
    private final JWTUtil jwtUtil;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());

        UserDto userDto = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(userDto));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(userDto.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());

    }

    public UserDto saveOrUpdate(OAuthAttributes attributes) {
        UserDto user = userMapper.selectUserByEmail(attributes.getEmail());
        if (user == null) {
            // 사용자가 없으면 새로 생성
            user = attributes.toEntity();
            userMapper.insertUser(user);
            return userMapper.selectUserByEmail(user.getEmail());
        } else {
            // 사용자가 있으면 정보 업데이트
            user.update(attributes.getName(), attributes.getPicture(), Role.USER);
            userMapper.updateUser(user);
            return userMapper.selectUserByEmail(user.getEmail());
        }

    }
}
