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
	public List<BoardDto> selectBoardByUserId(int userId, int offset, int limit) {
		return boardMapper.selectBoardByUserId(userId, offset, limit);
	}

	@Override
	public List<BoardDto> selectBoardListByUserId(int userId) {
		return boardMapper.selectBoardListByUserId(userId);
	}

	@Override
	public BoardDto selectBoardById(int boardId) {
		return boardMapper.selectBoardById(boardId);
	}

	@Override
	public int insertBoard(BoardDto boardDto) {
		boardMapper.insertBoard(boardDto);
		int boardId = boardMapper.selectBoardIdByUserId(boardDto.getUserId());
		return boardId;
	}

	@Override
	public void updateBoard(BoardDto boardDto) {
		boardMapper.updateBoard(boardDto);
	}

	@Override
	public void updateThumbnail(BoardDto boardDto) {
		boardMapper.updateThumbnail(boardDto);
	}

	@Override
	public void deleteBoard(int boardId) {
		boardMapper.deleteBoard(boardId);
	}

}