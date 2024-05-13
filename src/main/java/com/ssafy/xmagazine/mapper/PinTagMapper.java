package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.pintag.PinTagDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PinTagMapper {
	@Insert("INSERT INTO PinTags (pin_id, tag_id) VALUES (#{pinId}, #{tagId})")
	void insertPinTag(PinTagDto pinTagDto);

	@Delete("DELETE FROM PinTags WHERE pin_id = #{pinId} AND tag_id = #{tagId}")
	void deletePinTag(@Param("pinId") int pinId, @Param("tagId") int tagId);

	@Select("SELECT pin_id FROM PinTags WHERE tag_id = #{tagId}")
	List<Integer> selectPinIdsByTagId(int tagId);

	@Select("SELECT tag_id FROM PinTags WHERE pin_id = #{pinId}")
	List<Integer> selectTagIdsByPinId(int pinId);
}