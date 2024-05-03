package com.ssafy.xmagazine.domain.content;

import java.util.List;

public interface ContentService {

	List<ContentDto> selectContentByMagazineId(int magazineId);

	List<ContentDto> selectContentByPageId(int pageId);

	ContentDto selectContentById(int id);

	void insertContent(ContentDto contentDto);

	void updateContent(ContentDto contentDto);

	void deleteContent(int id);
}