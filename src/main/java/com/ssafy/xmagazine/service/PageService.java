package com.ssafy.xmagazine.service;

import java.util.List;

import com.ssafy.xmagazine.dto.PageDto;

public interface PageService {

	List<PageDto> selectPageByMagazineId(int magazineId);

	PageDto selectPageById(int id);

	void insertPage(PageDto pageDto);

	void updatePage(PageDto pageDto);

	void deletePage(int id);
}