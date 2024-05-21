package com.ssafy.xmagazine.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "UserDto")
public class UserDto {
	@Schema(name = "userId", description = "사용자 고유 ID")
	private int userId;

	@Schema(name = "username", description = "사용자 이름")
	private String username;

	@Schema(name = "email", description = "사용자 이메일")
	private String email;

	@Schema(name = "password", description = "로그인 시 입력받은 사용자 비밀번호")
	private String password;

	@Schema(name = "passwordHash", description = "비밀번호 해시값")
	private String passwordHash;

	@Schema(name = "profilePicture", description = "프로필 사진 URL")
	private String profilePicture;

	@Schema(name = "createdAt", description = "가입 날짜")
	private LocalDateTime createdAt;

	@Schema(name = "lastLogin", description = "마지막 로그인 날짜")
	private LocalDateTime lastLogin;

	public UserDto(String name, String email) {
		this.username = name;
		this.email = email;
	}
}
