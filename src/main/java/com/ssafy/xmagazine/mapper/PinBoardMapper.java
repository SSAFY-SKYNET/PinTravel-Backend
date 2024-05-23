package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.pinboard.PinBoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PinBoardMapper {
	@Insert("INSERT INTO pinboards (pin_id, board_id, user_id) VALUES (#{pinId}, #{boardId}, #{userId})")
	void insertPinBoard(PinBoardDto pinBoardDto);

	@Delete("DELETE FROM pinboards WHERE pin_id = #{pinId} AND board_id = #{boardId} AND user_id = #{userId}")
	void deletePinBoard(@Param("pinId") int pinId, @Param("boardId") int boardId, @Param("userId") int userId);

	@Select("SELECT pin_id FROM pinboards WHERE board_id = #{boardId}")
	List<Integer> selectPinIdsByBoardId(int boardId);

	@Select("SELECT board_id FROM pinboards WHERE pin_id = #{pinId}")
	List<Integer> selectBoardIdsByPinId(int pinId);
}
