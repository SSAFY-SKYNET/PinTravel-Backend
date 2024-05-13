package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.like.LikeDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {
	@Insert("INSERT INTO Likes (pin_id, user_id) VALUES (#{pinId}, #{userId})")
	void insertLike(LikeDto likeDto);

	@Delete("DELETE FROM Likes WHERE like_id = #{likeId}")
	void deleteLike(int likeId);

	@Select("SELECT * FROM Likes WHERE pin_id = #{pinId}")
	List<LikeDto> selectLikesByPin(int pinId);

	@Select("SELECT * FROM Likes WHERE user_id = #{userId}")
	List<LikeDto> selectLikesByUser(int userId);

	@Select("SELECT * FROM Likes WHERE pin_id = #{pinId} AND user_id = #{userId}")
	LikeDto selectLike(LikeDto likeDto);

	@Select("SELECT * FROM Likes WHERE like_id = #{likeId}")
	LikeDto selectLikeById(int likeId);
}