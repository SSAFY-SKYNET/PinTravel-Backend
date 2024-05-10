package com.ssafy.xmagazine.domain.user;

import java.util.List;

public interface UserService {

    // 기본 CRUD
    public void insertUser(UserDto userDto);

    public void updateUser(UserDto userDto);

    public void deleteUser(int userId);

    public List<UserDto> selectAllUser();

    public UserDto selectUserById(int userId);

    public void login(UserDto userDto);

    public void logout(UserDto userDto);

}