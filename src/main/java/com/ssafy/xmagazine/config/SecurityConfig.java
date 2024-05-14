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
				// .requestMatchers("/admin/**").permitAll()
				// .requestMatchers("/user/**").permitAll()
				// .requestMatchers("/magazine/**").permitAll()
				// .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
				// .requestMatchers("/login", "/register").permitAll() // "/login", "/register"

				.anyRequest().authenticated());

		return http.build(); // SecurityFilterChain 객체 반환
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
