package com.ssafy.xmagazine.domain.pin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.xmagazine.mapper.PinMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PinServiceImpl implements PinService {

	private final PinMapper pinMapper;
	private final ObjectMapper objectMapper;

	@Autowired
	public PinServiceImpl(PinMapper pinMapper, ObjectMapper objectMapper) {
		this.pinMapper = pinMapper;
		this.objectMapper = objectMapper;
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
	public List<PinDto> selectPinByPinIdAndPage(double longitude, double latitude, int offset, int limit) {
		return pinMapper.selectPinByPinIdAndPage(longitude, latitude, offset, limit);
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

	// Mapper를 활용한 검색
	@Override
	public List<PinDto> selectPinByMultiTagAndPage(List<String> tagNames, int offset, int limit) {
		List<PinDto> pinList = pinMapper.selectPinByMultiTagAndPage(tagNames, offset, limit);
		log.debug("pinList: {}", pinList);
		return pinList;
	}
}
