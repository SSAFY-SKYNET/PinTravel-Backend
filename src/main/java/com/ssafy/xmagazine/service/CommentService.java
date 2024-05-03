package com.ssafy.xmagazine.service;

import java.util.List;

import com.ssafy.xmagazine.dto.CommentDto;

public interface CommentService {

	List<CommentDto> selectCommentByMagazineId(int magazineId);

	CommentDto selectCommentById(int id);

	void insertComment(CommentDto commentDto);

	void updateComment(CommentDto commentDto);

	void deleteComment(int id);
}