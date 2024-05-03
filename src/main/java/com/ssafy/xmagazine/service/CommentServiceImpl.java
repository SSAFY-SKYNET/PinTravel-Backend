package com.ssafy.xmagazine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xmagazine.dto.CommentDto;
import com.ssafy.xmagazine.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

	private final CommentMapper commentMapper;

	@Override
	public List<CommentDto> selectCommentByMagazineId(int magazineId) {
		return commentMapper.selectCommentByMagazineId(magazineId);
	}

	@Override
	public CommentDto selectCommentById(int id) {
		return commentMapper.selectCommentById(id);
	}

	@Override
	public void insertComment(CommentDto commentDto) {
		commentMapper.insertComment(commentDto);
	}

	@Override
	public void updateComment(CommentDto commentDto) {
		commentMapper.updateComment(commentDto);
	}

	@Override
	public void deleteComment(int id) {
		commentMapper.deleteComment(id);
	}
}