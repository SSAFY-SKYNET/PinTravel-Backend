package com.ssafy.xmagazine.domain.comment;

import java.util.List;

public interface CommentService {

	List<CommentDto> selectCommentByPinId(int pinId);
	List<CommentDto> selectCommentByUserId(int userId);

	CommentDto selectCommentById(int id);

	void insertComment(CommentDto commentDto);

	void updateComment(CommentDto commentDto);

	void deleteComment(int id);

}