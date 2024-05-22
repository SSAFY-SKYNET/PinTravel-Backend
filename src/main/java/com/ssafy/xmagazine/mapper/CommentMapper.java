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
	@Select("select * from comments where pin_id = #{pinId} order by created_at asc")
	List<CommentDto> selectCommentByPinId(int pinId);

	@Select("select * from comments where user_id = #{userId}")
	List<CommentDto> selectCommentByUserId(int userId);

	@Select("select * from comments where comment_id = #{commentId}")
	CommentDto selectCommentById(int commentId);

	@Insert("insert into comments (pin_id, user_id, text) values (#{pinId}, #{userId}, #{text})")
	void insertComment(CommentDto commentDto);

	@Update("update comments set text = #{text} where comment_id = #{commentId}")
	void updateComment(CommentDto commentDto);

	@Delete("delete from comments where comment_id = #{commentId}")
	void deleteComment(int commentId);
}
