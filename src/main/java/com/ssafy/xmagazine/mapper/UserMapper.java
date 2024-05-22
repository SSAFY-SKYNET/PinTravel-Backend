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
    @Select("SELECT * FROM users WHERE email = #{email}")
    UserDto selectUserByEmail(String email);

    @Insert("INSERT INTO users (username, email, password_hash, profile_picture) VALUES (#{username}, #{email}, #{passwordHash}, #{profilePicture})")
    int insertUser(UserDto user);

    @Update("UPDATE users SET username = #{username}, password_hash = #{passwordHash}, profile_picture = #{profilePicture} WHERE user_id = #{userId}")
    int updateUser(UserDto user);

    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    void deleteUser(int userId);

    @Select("SELECT * FROM users")
    List<UserDto> selectAllUser();

    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    UserDto selectUserById(int userId);

    @Update("UPDATE users SET token = #{token} WHERE user_id = #{userId}")
    void saveRefreshToken(int userId, String token);

    @Select("SELECT token FROM users WHERE user_id = #{userId}")
    String getRefreshToken(int userId);

    @Update("UPDATE users SET token = NULL WHERE user_id = #{userId}")
    void deleteRefreshToken(int userId);

}
