package com.ssafy.service;

import java.util.List;

import com.ssafy.dto.UserDto;

public interface UserService {

    // 기본 CRUD
    public void insertUser(UserDto userDto);

    public void updateUser(UserDto userDto);

    public void deleteUser(int userId);

    public UserDto getUser(int userId);

    public List<UserDto> getAllUser();
}
