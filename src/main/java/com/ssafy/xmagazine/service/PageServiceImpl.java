package com.ssafy.xmagazine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xmagazine.dto.PageDto;
import com.ssafy.xmagazine.repository.PageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PageServiceImpl implements PageService {

	private final PageMapper pageMapper;

	@Override
	public List<PageDto> selectPageByMagazineId(int magazineId) {
		return pageMapper.selectPageByMagazineId(magazineId);
	}

	@Override
	public PageDto selectPageById(int id) {
		return pageMapper.selectPageById(id);
	}

	@Override
	public void insertPage(PageDto pageDto) {
		pageMapper.insertPage(pageDto);
	}

	@Override
	public void updatePage(PageDto pageDto) {
		pageMapper.updatePage(pageDto);
	}

	@Override
	public void deletePage(int id) {
		pageMapper.deletePage(id);
	}
}