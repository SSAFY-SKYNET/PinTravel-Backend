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
        String hashedPassword = passwordEncoder.encode(userDto.getPasswordHash());
        userDto.setPasswordHash(hashedPassword);

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
        UserDto storedUser = userMapper.selectUserByEmail(userDto.getEmail());

        // 저장된 해시와 입력된 비밀번호 비교
        if (storedUser != null && passwordEncoder.matches(userDto.getPasswordHash(), storedUser.getPasswordHash())) {
            userMapper.login(userDto);
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    @Override
    public void logout(UserDto userDto) {
        userMapper.logout(userDto);
    }

}
