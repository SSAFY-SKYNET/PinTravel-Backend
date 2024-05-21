package com.ssafy.xmagazine.domain.like;

import java.util.List;

public interface LikeService {
	void insertLike(int pinId, int userId);
	void deleteLike(int pinId, int userId);
	List<LikeDto> selectLikesByPin(int pinId);
	List<LikeDto> selectLikesByUser(int userId);
	LikeDto selectLike(LikeDto likeDto);
}