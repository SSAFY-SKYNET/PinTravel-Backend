package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.like.LikeDto;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LikeMapper {
	@Insert("INSERT INTO likes (pin_id, user_id) VALUES (#{pinId}, #{userId})")
	void insertLike(int pinId, int userId);

	@Delete("DELETE FROM likes WHERE pin_id = #{pinId} AND user_id = #{userId}")
	void deleteLike(int pinId, int userId);

	@Select("SELECT * FROM likes WHERE pin_id = #{pinId}")
	List<LikeDto> selectLikesByPin(int pinId);

	@Select("SELECT * FROM likes WHERE user_id = #{userId}")
	List<LikeDto> selectLikesByUser(int userId);

	@Select("SELECT * FROM likes WHERE pin_id = #{pinId} AND user_id = #{userId}")
	LikeDto selectLike(LikeDto likeDto);

	@Select("SELECT * FROM likes WHERE like_id = #{likeId}")
	LikeDto selectLikeById(int likeId);
}
