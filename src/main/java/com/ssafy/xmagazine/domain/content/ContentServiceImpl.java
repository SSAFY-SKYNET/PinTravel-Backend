package com.ssafy.xmagazine.domain.content;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentServiceImpl implements ContentService {

	private final ContentMapper contentMapper;

	@Override
	public List<ContentDto> selectContentByMagazineId(int magazineId) {
		return contentMapper.selectContentByMagazineId(magazineId);
	}

	@Override
	public List<ContentDto> selectContentByPageId(int pageId) {
		return contentMapper.selectContentByPageId(pageId);
	}

	@Override
	public ContentDto selectContentById(int id) {
		return contentMapper.selectContentById(id);
	}

	@Override
	public void insertContent(ContentDto contentDto) {
		contentMapper.insertContent(contentDto);
	}

	@Override
	public void updateContent(ContentDto contentDto) {
		contentMapper.updateContent(contentDto);
	}

	@Override
	public void deleteContent(int id) {
		contentMapper.deleteContent(id);
	}
}