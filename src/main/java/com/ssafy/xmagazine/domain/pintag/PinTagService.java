package com.ssafy.xmagazine.domain.pintag;

import java.util.List;

public interface PinTagService {
	void insertPinTag(PinTagDto pinTagDto);
	void deletePinTag(int pinId, int tagId);
	List<Integer> selectPinIdsByTagId(int tagId);
	List<Integer> selectTagIdsByPinId(int pinId);
}