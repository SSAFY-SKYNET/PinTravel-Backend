package com.ssafy.xmagazine.service;

import java.util.List;

import com.ssafy.xmagazine.dto.UserDto;

public interface UserService {

    // 기본 CRUD
    public void insertUser(UserDto userDto);

    public void updateUser(UserDto userDto);

    public void deleteUser(int userId);

    public List<UserDto> getAllUser();

    public UserDto selectUserById(int userId);

}
