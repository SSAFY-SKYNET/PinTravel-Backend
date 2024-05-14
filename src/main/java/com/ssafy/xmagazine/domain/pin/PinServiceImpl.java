package com.ssafy.xmagazine.domain.pin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xmagazine.mapper.PinMapper;

@Service
@Transactional
public class PinServiceImpl implements PinService {

	private final PinMapper pinMapper;

	public PinServiceImpl(PinMapper pinMapper) {
		this.pinMapper = pinMapper;
	}

	@Override
	public PinDto selectPinById(int pinId) {
		return pinMapper.selectPinById(pinId);
	}

	@Override
	public List<PinDto> selectPinByLikeCount() {
		return pinMapper.selectPinByLikeCount();
	}

	@Override
	public List<PinDto> selectPinByLikeCountAndPage(int offset, int limit) {
		return pinMapper.selectPinByLikeCountAndPage(offset, limit);
	}

	@Override
	public List<PinDto> selectPinByUserId(int userId) {
		return pinMapper.selectPinByUserId(userId);
	}

	@Override
	public List<PinDto> selectPinByUserIdAndPage(int userId, int offset, int limit) {
		return pinMapper.selectPinByUserIdAndPage(userId, offset, limit);
	}

	@Override
	public List<PinDto> selectPinByTag(int tagId) {
		return pinMapper.selectPinByTag(tagId);
	}

	@Override
	public List<PinDto> selectPinByTagAndPage(int tagId, int offset, int limit) {
		return pinMapper.selectPinByTagAndPage(tagId, offset, limit);
	}

	@Override
	public List<PinDto> selectPinByBoard(int boardId) {
		return pinMapper.selectPinByBoard(boardId);
	}

	@Override
	public List<PinDto> selectPinByBoardAndPage(int boardId, int offset, int limit) {
		return pinMapper.selectPinByBoardAndPage(boardId, offset, limit);
	}

	@Override
	public void insertPin(PinDto pin) {
		pinMapper.insertPin(pin);
	}

	@Override
	public void updatePin(PinDto pin) {
		pinMapper.updatePin(pin);
	}

	@Override
	public void deletePin(int pinId) {
		pinMapper.deletePin(pinId);
	}
}
