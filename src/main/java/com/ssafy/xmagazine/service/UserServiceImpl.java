package com.ssafy.xmagazine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.dto.UserDto;
import com.ssafy.xmagazine.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

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
        userMapper.insertUser(userDto);
    }

    @Override
    public UserDto selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public void updateUser(UserDto userDto) {
        userMapper.updateUser(userDto);
    }

    @Override
    public void login(UserDto userDto) {
        userMapper.login(userDto);
    }

    @Override
    public void logout(UserDto userDto) {
        userMapper.logout(userDto);
    }

}
