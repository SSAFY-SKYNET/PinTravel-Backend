package com.ssafy.xmagazine.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xmagazine.repository.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{
	private final CommentMapper commentMapper;
}
