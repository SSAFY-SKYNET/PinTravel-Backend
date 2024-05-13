package com.ssafy.xmagazine.domain.follower;

import java.util.List;

import com.ssafy.xmagazine.domain.user.UserDto;

public interface FollowerService {
	void insertFollower(FollowerDto followerDto);
	void deleteFollower(int followerId, int followedId);
	List<UserDto> selectFollowers(int followedId);
	List<UserDto> selectFollowings(int followerId);
}