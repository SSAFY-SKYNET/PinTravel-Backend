package com.ssafy.xmagazine.domain.like;

import com.ssafy.xmagazine.domain.pin.PinDto;
import com.ssafy.xmagazine.domain.user.UserDto;
import com.ssafy.xmagazine.mapper.LikeMapper;
import com.ssafy.xmagazine.mapper.PinMapper;
import com.ssafy.xmagazine.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

	private final LikeMapper likeMapper;
	private final PinMapper pinMapper;
	private final UserMapper userMapper;

	public LikeServiceImpl(LikeMapper likeMapper, PinMapper pinMapper, UserMapper userMapper) {
		this.likeMapper = likeMapper;
		this.pinMapper = pinMapper;
		this.userMapper = userMapper;
	}

	@Override
	public void insertLike(LikeDto likeDto) {
		PinDto pin = pinMapper.selectPinById(likeDto.getPinId());
		UserDto user = userMapper.selectUserById(likeDto.getUserId());

		if (pin == null) {
			throw new IllegalArgumentException("Pin does not exist.");
		}
		if (user == null) {
			throw new IllegalArgumentException("User does not exist.");
		}
		if (likeMapper.selectLike(likeDto) != null) {
			throw new IllegalArgumentException("User already liked this pin.");
		}

		likeMapper.insertLike(likeDto);
	}

	@Override
	public void deleteLike(int likeId) {
		if (likeMapper.selectLikeById(likeId) == null) {
			throw new IllegalArgumentException("Like does not exist.");
		}

		likeMapper.deleteLike(likeId);
	}

	@Override
	public List<LikeDto> selectLikesByPin(int pinId) {
		return likeMapper.selectLikesByPin(pinId);
	}

	@Override
	public List<LikeDto> selectLikesByUser(int userId) {
		return likeMapper.selectLikesByUser(userId);
	}

	@Override
	public LikeDto selectLike(LikeDto likeDto) {
		return likeMapper.selectLike(likeDto);
	}
}