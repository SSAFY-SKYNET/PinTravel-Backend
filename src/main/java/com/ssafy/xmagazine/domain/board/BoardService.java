package com.ssafy.xmagazine.domain.board;

import java.util.List;

public interface BoardService {

	List<BoardDto> selectBoardByUserId(int userId);

	BoardDto selectBoardById(int boardId);

	void insertBoard(BoardDto boardDto);

	void updateBoard(BoardDto boardDto);

	void deleteBoard(int boardId);
}