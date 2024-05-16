package com.ssafy.xmagazine.domain.user;

import java.util.List;

public interface UserService {

	// 기본 CRUD
	public void insertUser(UserDto userDto);

	public void updateUser(UserDto userDto);

	public void deleteUser(int userId);

	public List<UserDto> selectAllUser();

	public UserDto selectUserById(int userId);

	public UserDto login(UserDto userDto);

	public void logout(UserDto userDto);

	public void saveRefreshToken(int userId, String refreshToken);

	public String getRefreshToken(int userId);

	public void deleteRefreshToken(int userId);
}
