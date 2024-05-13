package com.ssafy.xmagazine.domain.follower;

import com.ssafy.xmagazine.domain.user.UserDto;
import com.ssafy.xmagazine.domain.user.UserService;
import com.ssafy.xmagazine.mapper.FollowerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FollowerServiceImpl implements FollowerService {

	private final FollowerMapper followerMapper;
	private final UserService userService;

	public FollowerServiceImpl(FollowerMapper followerMapper, UserService userService) {
		this.followerMapper = followerMapper;
		this.userService = userService;
	}

	@Override
	public void insertFollower(FollowerDto followerDto) {
		// 팔로워와 팔로잉 사용자가 존재하는지 확인
		UserDto follower = userService.selectUserById(followerDto.getFollowerId());
		UserDto followed = userService.selectUserById(followerDto.getFollowedId());

		if (follower != null && followed != null) {
			followerMapper.insertFollower(followerDto);
		} else {
			throw new IllegalArgumentException("Invalid follower or followed user.");
		}
	}

	@Override
	public void deleteFollower(int followerId, int followedId) {
		followerMapper.deleteFollower(followerId, followedId);
	}

	@Override
	public List<UserDto> selectFollowers(int followedId) {
		List<FollowerDto> followers = followerMapper.selectFollowers(followedId);

		// 팔로워 ID 목록을 UserDto 목록으로 변환
		return followers.stream()
			.map(follower -> userService.selectUserById(follower.getFollowerId()))
			.collect(Collectors.toList());
	}

	@Override
	public List<UserDto> selectFollowings(int followerId) {
		List<FollowerDto> followings = followerMapper.selectFollowings(followerId);

		// 팔로잉 ID 목록을 UserDto 목록으로 변환
		return followings.stream()
			.map(following -> userService.selectUserById(following.getFollowedId()))
			.collect(Collectors.toList());
	}
}