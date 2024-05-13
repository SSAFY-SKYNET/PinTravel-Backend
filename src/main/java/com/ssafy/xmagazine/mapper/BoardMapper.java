package com.ssafy.xmagazine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.domain.board.BoardDto;

@Mapper
public interface BoardMapper {

	@Select("SELECT * FROM Boards WHERE user_id = #{userId}")
	List<BoardDto> selectBoardByUserId(int userId);

	@Select("SELECT * FROM Boards WHERE board_id = #{boardId}")
	BoardDto selectBoardById(int boardId);

	@Insert("INSERT INTO Boards (user_id, title, description, is_private) VALUES (#{userId}, #{title}, #{description}, #{isPrivate})")
	void insertBoard(BoardDto boardDto);

	@Update("UPDATE Boards SET title = #{title}, description = #{description}, is_private = #{isPrivate} WHERE board_id = #{boardId}")
	void updateBoard(BoardDto boardDto);

	@Delete("DELETE FROM Boards WHERE board_id = #{boardId}")
	void deleteBoard(int boardId);
}