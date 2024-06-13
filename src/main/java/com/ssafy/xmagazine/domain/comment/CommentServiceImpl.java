package com.ssafy.xmagazine.domain.comment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xmagazine.mapper.CommentMapper;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	private final CommentMapper commentMapper;

	public CommentServiceImpl(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	public List<CommentDto> selectCommentByPinId(int pinId) {
		return commentMapper.selectCommentByPinId(pinId);
	}

	@Override
	public List<CommentDto> selectCommentByUserId(int userId) {
		return commentMapper.selectCommentByUserId(userId);
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