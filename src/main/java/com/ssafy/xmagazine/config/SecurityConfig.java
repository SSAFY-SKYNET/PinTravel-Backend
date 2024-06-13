package com.ssafy.xmagazine.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ssafy.xmagazine.oauth.CustomAuthenticationSuccessHandler;
import com.ssafy.xmagazine.oauth.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomAuthenticationSuccessHandler successHandler;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)

				.formLogin(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/hc", "/env").permitAll()
						.anyRequest().authenticated())
				.oauth2Login(oauth2 -> oauth2
						.successHandler(successHandler)
						.loginPage("/oauth2/authorization/google") // 권한 접근 실패 시 로그인 페이지로 이동
						.failureUrl("/loginFailure") // 로그인 실패 시 이동 페이지
						.userInfoEndpoint(userInfo -> userInfo
								.userService(customOAuth2UserService)));

		return http.build(); // SecurityFilterChain 객체 반환
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}