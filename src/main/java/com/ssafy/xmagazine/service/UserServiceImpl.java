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
        // TODO Auto-generated method stub

    }

    @Override
    public List<UserDto> getAllUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertUser(UserDto userDto) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserDto selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public void updateUser(UserDto userDto) {
        // TODO Auto-generated method stub

    }

}
