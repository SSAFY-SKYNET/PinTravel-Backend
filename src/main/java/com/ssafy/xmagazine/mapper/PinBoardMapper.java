package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.pinboard.PinBoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PinBoardMapper {
	@Insert("INSERT INTO PinBoards (pin_id, board_id) VALUES (#{pinId}, #{boardId})")
	void insertPinBoard(PinBoardDto pinBoardDto);

	@Delete("DELETE FROM PinBoards WHERE pin_id = #{pinId} AND board_id = #{boardId}")
	void deletePinBoard(@Param("pinId") int pinId, @Param("boardId") int boardId);

	@Select("SELECT pin_id FROM PinBoards WHERE board_id = #{boardId}")
	List<Integer> selectPinIdsByBoardId(int boardId);

	@Select("SELECT board_id FROM PinBoards WHERE pin_id = #{pinId}")
	List<Integer> selectBoardIdsByPinId(int pinId);
}