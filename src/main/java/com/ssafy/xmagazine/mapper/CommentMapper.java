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

	@Select("SELECT * FROM comments WHERE pin_id = #{pinId} ORDER BY created_at ASC")
	List<CommentDto> selectCommentByPinId(int pinId);

	@Select("SELECT * FROM comments WHERE user_id = #{userId}")
	List<CommentDto> selectCommentByUserId(int userId);

	@Select("SELECT * FROM comments WHERE comment_id = #{commentId}")
	CommentDto selectCommentById(int commentId);

	@Insert("INSERT INTO comments (pin_id, user_id, text) VALUES (#{pinId}, #{userId}, #{text})")
	void insertComment(CommentDto commentDto);

	@Update("UPDATE comments SET text = #{text} WHERE comment_id = #{comment_id}")
	void updateComment(CommentDto commentDto);

	@Delete("DELETE FROM comments WHERE comment_id = #{commentId}")
	void deleteComment(int commentId);

}
