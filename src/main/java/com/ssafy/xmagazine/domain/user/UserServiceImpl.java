package com.ssafy.xmagazine.domain.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void deleteUser(int userId) {
		userMapper.deleteUser(userId);
	}

	@Override
	public List<UserDto> selectAllUser() {
		return userMapper.selectAllUser();
	}

	@Override
	public void insertUser(UserDto userDto) {
		// 비밀번호 해시
		if (userDto.getPassword() != null) {
			String hashedPassword = passwordEncoder.encode(userDto.getPassword());
			userDto.setPasswordHash(hashedPassword);
		}

		userMapper.insertUser(userDto);
	}

	@Override
	public UserDto selectUserById(int userId) {
		return userMapper.selectUserById(userId);
	}

	@Override
	public void updateUser(UserDto userDto) {
		String hashedPassword = passwordEncoder.encode(userDto.getPassword());
		userDto.setPasswordHash(hashedPassword);

		userMapper.updateUser(userDto);
	}

	@Override
	public UserDto login(UserDto userDto) {
		UserDto storedUser = userMapper.selectUserByEmail(userDto.getEmail());

		// 저장된 해시와 입력된 비밀번호의 해시값 비교
		if (storedUser != null && passwordEncoder.matches(userDto.getPassword(), storedUser.getPasswordHash())) {
			return storedUser;
		} else {
			throw new IllegalArgumentException("Invalid email or password");
		}
	}

	@Override
	public void logout(UserDto userDto) {
		deleteRefreshToken(userDto.getUserId());
	}

	@Override
	public void saveRefreshToken(int userId, String refreshToken) {
		userMapper.saveRefreshToken(userId, refreshToken);
	}

	@Override
	public String getRefreshToken(int userId) {
		return userMapper.getRefreshToken(userId);
	}

	@Override
	public void deleteRefreshToken(int userId) {
		userMapper.deleteRefreshToken(userId);
	}

	@Override
	public UserDto selectUserByEmail(String email) {
		return userMapper.selectUserByEmail(email);
	}

	public boolean isEmailAvailable(String email) {
		return userMapper.selectUserByEmail(email) == null;
	}

	public void addUser(UserDto userDto) throws Exception {
		if (!isEmailAvailable(userDto.getEmail())) {
			throw new Exception("이메일이 이미 사용 중입니다.");
		}
		userMapper.insertUser(userDto);
	}

	@Override
	public UserDto oauthLogin(UserDto userDto) {
		UserDto existingUser = userMapper.selectUserByEmail(userDto.getEmail());
		if (existingUser == null) {
			userMapper.insertUser(userDto);
		}
		return existingUser;
	}
}
