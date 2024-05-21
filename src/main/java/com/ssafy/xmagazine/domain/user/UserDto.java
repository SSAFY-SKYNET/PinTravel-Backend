package com.ssafy.xmagazine.domain.user;

import java.time.LocalDateTime;

import com.ssafy.xmagazine.oauth.Role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "UserDto")
@Builder
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

	private Role role;

	@Builder
	public UserDto(int userId, String username, String email, String password, String profilePicture,
			LocalDateTime createdAt, LocalDateTime lastLogin, Role role) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
		this.createdAt = createdAt;
		this.lastLogin = lastLogin;
		this.role = role;
	}

	public UserDto update(String username, String profilePicture, Role role) {
		this.username = username;
		this.profilePicture = profilePicture;
		this.role = role; // role도 업데이트
		return this;
	}

	public String getRoleKey() {
		if (this.role == null) {
			return Role.GUEST.getKey(); // 기본값으로 GUEST의 키를 반환하거나
			// throw new IllegalStateException("Role is not set."); // 예외를 발생시킬 수 있습니다.
		}
		return this.role.getKey();
	}

}
