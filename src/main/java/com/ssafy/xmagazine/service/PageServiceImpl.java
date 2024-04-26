package com.ssafy.xmagazine.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xmagazine.repository.PageMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
	private final PageMapper pageMapper;
}
