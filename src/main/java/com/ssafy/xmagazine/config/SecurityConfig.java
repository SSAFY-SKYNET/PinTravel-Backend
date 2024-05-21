package com.ssafy.xmagazine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.cors(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.formLogin(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(t -> t
						.requestMatchers("/**").permitAll()
						.anyRequest().authenticated())
				.oauth2Login(oauth2 -> oauth2
						.loginPage("/oauth2/authorization/google") // 권한 접근 실패 시 로그인 페이지로 이동
						.defaultSuccessUrl("/loginSuccess") // 로그인 성공 시 이동할 페이지
						.failureUrl("/loginFailure") // 로그인 실패 시 이동 페이지
				)
				.logout(logout -> logout
						.logoutUrl("/logout") // 로그아웃 URL
						.logoutSuccessUrl("/") // 로그아웃 성공 시 이동 페이지
				);

		return http.build(); // SecurityFilterChain 객체 반환
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
