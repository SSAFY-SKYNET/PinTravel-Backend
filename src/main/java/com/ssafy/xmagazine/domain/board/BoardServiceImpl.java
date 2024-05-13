package com.ssafy.xmagazine.domain.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xmagazine.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardDto> selectBoardByUserId(int userId) {
		return boardMapper.selectBoardByUserId(userId);
	}

	@Override
	public BoardDto selectBoardById(int boardId) {
		return boardMapper.selectBoardById(boardId);
	}

	@Override
	public void insertBoard(BoardDto boardDto) {
		boardMapper.insertBoard(boardDto);
	}

	@Override
	public void updateBoard(BoardDto boardDto) {
		boardMapper.updateBoard(boardDto);
	}

	@Override
	public void deleteBoard(int boardId) {
		boardMapper.deleteBoard(boardId);
	}

}