package com.ssafy.xmagazine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.domain.comment.CommentDto;

@Mapper
public interface CommentMapper {

	@Select("SELECT * FROM comment WHERE magazine_id = #{magazineId}")
	List<CommentDto> selectCommentByPinId(int pinId);

	@Select("SELECT * FROM comment WHERE magazine_id = #{magazineId}")
	List<CommentDto> selectCommentByUserId(int userId);

	@Select("SELECT * FROM comment WHERE id = #{id}")
	CommentDto selectCommentById(int id);

	@Insert("INSERT INTO comment (magazine_id, user_id, content) VALUES (#{magazineId}, #{userId}, #{content})")
	void insertComment(CommentDto commentDto);

	@Update("UPDATE comment SET content = #{content} WHERE id = #{id}")
	void updateComment(CommentDto commentDto);

	@Delete("DELETE FROM comment WHERE id = #{id}")
	void deleteComment(int id);
}