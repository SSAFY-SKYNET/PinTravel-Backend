package com.ssafy.xmagazine.domain.pinboard;

import com.ssafy.xmagazine.mapper.PinBoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PinBoardServiceImpl implements PinBoardService {

	private final PinBoardMapper pinBoardMapper;

	public PinBoardServiceImpl(PinBoardMapper pinBoardMapper) {
		this.pinBoardMapper = pinBoardMapper;
	}

	@Override
	public void insertPinBoard(PinBoardDto pinBoardDto) {
		pinBoardMapper.insertPinBoard(pinBoardDto);
	}

	@Override
	public void deletePinBoard(int pinId, int boardId, int userId) {
		pinBoardMapper.deletePinBoard(pinId, boardId, userId);
	}

	@Override
	public List<Integer> selectPinIdsByBoardId(int boardId) {
		return pinBoardMapper.selectPinIdsByBoardId(boardId);
	}

	@Override
	public List<Integer> selectBoardIdsByPinId(int pinId) {
		return pinBoardMapper.selectBoardIdsByPinId(pinId);
	}
}