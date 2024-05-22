package com.ssafy.xmagazine.domain.pin;

import java.util.List;

public interface PinService {
	PinDto selectPinById(int pinId);

	List<PinDto> selectPinByLikeCount();

	List<PinDto> selectPinByLikeCountAndPage(int offset, int limit);

	List<PinDto> selectPinByUserId(int userId);

	List<PinDto> selectPinByUserIdAndPage(int userId, int offset, int limit);

	List<PinDto> selectPinByTag(int tagId);

	List<PinDto> selectPinByTagAndPage(int tagId, int offset, int limit);

	List<PinDto> selectPinByBoard(int boardId);

	List<PinDto> selectPinByBoardAndPage(int boardId, int offset, int limit);

	List<PinDto> selectPinByPinIdAndPage(double longitude, double latitude, int offset, int limit);

	void insertPin(PinDto pin);

	void updatePin(PinDto pin);

	void deletePin(int pinId);

	List<PinDto> selectPinByMultiTagAndPage(List<String> tagNames, int offset, int limit);

}
