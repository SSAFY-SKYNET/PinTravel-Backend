package com.ssafy.xmagazine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.domain.board.BoardDto;

@Mapper
public interface BoardMapper {

	@Select("select * from boards where user_id = #{userId} and is_deleted = 0 order by created_at desc limit #{limit} offset #{offset}")
	List<BoardDto> selectBoardByUserId(@Param("userId") int userId, @Param("offset") int offset,
			@Param("limit") int limit);

	@Select("select * from boards where user_id = #{userId} and is_deleted = 0 order by created_at desc")
	List<BoardDto> selectBoardListByUserId(@Param("userId") int userId);

	@Select("select board_id from boards where user_id = #{userId} and is_deleted = 0 order by board_id desc limit 1")
	int selectBoardIdByUserId(int userId);

	@Select("select * from boards where board_id = #{boardId} and is_deleted = 0")
	BoardDto selectBoardById(int boardId);

	@Insert("insert into boards (user_id, thumbnail, title, description, is_private) values (#{userId}, #{thumbnail}, #{title}, #{description}, #{isPrivate})")
	void insertBoard(BoardDto boardDto);

	@Update("update boards set title = #{title}, description = #{description}, is_private = #{isPrivate} where board_id = #{boardId}")
	void updateBoard(BoardDto boardDto);

	@Update("update boards set thumbnail = #{thumbnail} where board_id = #{boardId}")
	void updateThumbnail(BoardDto boardDto);

	@Update("update boards set is_deleted = 1 where board_id = #{boardId}")
	void deleteBoard(int boardId);
}