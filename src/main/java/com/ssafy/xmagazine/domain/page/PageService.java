package com.ssafy.xmagazine.domain.page;

import java.util.List;

public interface PageService {

	List<PageDto> selectPageByMagazineId(int magazineId);

	PageDto selectPageById(int id);

	void insertPage(PageDto pageDto);

	void updatePage(PageDto pageDto);

	void deletePage(int id);
}