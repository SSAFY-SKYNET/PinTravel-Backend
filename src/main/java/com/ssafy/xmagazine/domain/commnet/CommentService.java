package com.ssafy.xmagazine.domain.commnet;

import java.util.List;

public interface CommentService {

	List<CommentDto> selectCommentByMagazineId(int magazineId);

	CommentDto selectCommentById(int id);

	void insertComment(CommentDto commentDto);

	void updateComment(CommentDto commentDto);

	void deleteComment(int id);
}