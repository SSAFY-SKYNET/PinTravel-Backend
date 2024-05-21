package com.ssafy.xmagazine.domain.pinboard;

import java.util.List;

public interface PinBoardService {
	void insertPinBoard(PinBoardDto pinBoardDto);
	void deletePinBoard(int pinId, int boardId, int userId);
	List<Integer> selectPinIdsByBoardId(int boardId);
	List<Integer> selectBoardIdsByPinId(int pinId);
}