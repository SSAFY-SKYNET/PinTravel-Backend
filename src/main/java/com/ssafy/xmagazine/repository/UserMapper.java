package com.ssafy.xmagazine.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.dto.UserDto;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (email, password, username, createdAt) VALUES (#{email}, #{password}, #{username}, #{createdAt})")
    void insertUser(UserDto user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDto selectUserById(int id);

    @Update("UPDATE user SET email = #{email}, password = #{password}, username = #{username}, createdAt = #{createdAt} WHERE id = #{id}")
    void updateUser(UserDto user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(int id);
}
