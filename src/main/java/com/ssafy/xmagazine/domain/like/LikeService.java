package com.ssafy.xmagazine.domain.like;

import java.util.List;

public interface LikeService {
	void insertLike(LikeDto likeDto);
	void deleteLike(int likeId);
	List<LikeDto> selectLikesByPin(int pinId);
	List<LikeDto> selectLikesByUser(int userId);
	LikeDto selectLike(LikeDto likeDto);
}