package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.follower.FollowerDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FollowerMapper {
	@Insert("INSERT INTO followers (follower_id, followed_id) VALUES (#{followerId}, #{followedId})")
	void insertFollower(FollowerDto followerDto);

	@Delete("DELETE FROM followers WHERE follower_id = #{followerId} AND followed_id = #{followedId}")
	void deleteFollower(@Param("followerId") int followerId, @Param("followedId") int followedId);

	@Select("SELECT * FROM followers WHERE followed_id = #{followedId}")
	List<FollowerDto> selectFollowers(int followedId);

	@Select("SELECT * FROM followers WHERE follower_id = #{followerId}")
	List<FollowerDto> selectFollowings(int followerId);
}
