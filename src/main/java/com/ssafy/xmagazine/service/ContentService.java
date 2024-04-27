package com.ssafy.xmagazine.service;

import java.util.List;

import com.ssafy.xmagazine.dto.ContentDto;

public interface ContentService {

	List<ContentDto> selectContentByMagazineId(int magazineId);

	List<ContentDto> selectContentByPageId(int pageId);

	ContentDto selectContentById(int id);

	void insertContent(ContentDto contentDto);

	void updateContent(ContentDto contentDto);

	void deleteContent(int id);
}