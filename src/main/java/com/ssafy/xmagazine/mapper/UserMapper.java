package com.ssafy.xmagazine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.domain.user.UserDto;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Users WHERE email = #{email}")
    UserDto selectUserByEmail(String email);

    @Insert("INSERT INTO Users (username, email, password_hash, profile_picture) VALUES (#{username}, #{email}, #{passwordHash}, #{profilePicture})")
    void insertUser(UserDto user);

    @Update("UPDATE Users SET username = #{username}, email = #{email}, password_hash = #{passwordHash}, profile_picture = #{profilePicture}, last_login = #{lastLogin} WHERE user_id = #{userId}")
    void updateUser(UserDto user);

    @Delete("DELETE FROM Users WHERE user_id = #{userId}")
    void deleteUser(int userId);

    @Select("SELECT * FROM Users")
    List<UserDto> selectAllUser();

    @Select("SELECT * FROM Users WHERE user_id = #{userId}")
    UserDto selectUserById(int userId);

    @Select("SELECT * FROM Users WHERE email = #{email} AND password_hash = #{passwordHash}")
    UserDto login(UserDto userDto);

    @Update("UPDATE Users SET last_login = CURRENT_TIMESTAMP WHERE user_id = #{userId}")
    void logout(UserDto userDto);
}