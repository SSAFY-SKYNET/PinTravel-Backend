package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.pinboard.PinBoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PinBoardMapper {
	@Insert("INSERT INTO PinBoards (pin_id, board_id, user_id) VALUES (#{pinId}, #{boardId}, #{userId})")
	void insertPinBoard(PinBoardDto pinBoardDto);

	@Delete("DELETE FROM PinBoards WHERE pin_id = #{pinId} AND board_id = #{boardId} AND user_id = #{userId}")
	void deletePinBoard(@Param("pinId") int pinId, @Param("boardId") int boardId, @Param("userId") int userId);

	@Select("SELECT pin_id FROM PinBoards WHERE board_id = #{boardId}")
	List<Integer> selectPinIdsByBoardId(int boardId);

	@Select("SELECT board_id FROM PinBoards WHERE pin_id = #{pinId}")
	List<Integer> selectBoardIdsByPinId(int pinId);
}