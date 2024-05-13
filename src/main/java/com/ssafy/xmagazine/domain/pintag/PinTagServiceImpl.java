package com.ssafy.xmagazine.domain.pintag;

import com.ssafy.xmagazine.mapper.PinTagMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PinTagServiceImpl implements PinTagService {

	private final PinTagMapper pinTagMapper;

	public PinTagServiceImpl(PinTagMapper pinTagMapper) {
		this.pinTagMapper = pinTagMapper;
	}

	@Override
	public void insertPinTag(PinTagDto pinTagDto) {
		pinTagMapper.insertPinTag(pinTagDto);
	}

	@Override
	public void deletePinTag(int pinId, int tagId) {
		pinTagMapper.deletePinTag(pinId, tagId);
	}

	@Override
	public List<Integer> selectPinIdsByTagId(int tagId) {
		return pinTagMapper.selectPinIdsByTagId(tagId);
	}

	@Override
	public List<Integer> selectTagIdsByPinId(int pinId) {
		return pinTagMapper.selectTagIdsByPinId(pinId);
	}
}