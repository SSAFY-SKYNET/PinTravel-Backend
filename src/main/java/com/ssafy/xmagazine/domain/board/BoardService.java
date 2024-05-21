package com.ssafy.xmagazine.domain.board;

import java.util.List;

public interface BoardService {

	List<BoardDto> selectBoardByUserId(int userId, int offset, int limit);

	List<BoardDto> selectBoardListByUserId(int userId);

	BoardDto selectBoardById(int boardId);

	int insertBoard(BoardDto boardDto);

	void updateBoard(BoardDto boardDto);

	void updateThumbnail(BoardDto boardDto);

	void deleteBoard(int boardId);
}