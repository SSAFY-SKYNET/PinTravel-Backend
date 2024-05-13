package com.ssafy.xmagazine.domain.tag;

import java.util.List;

public interface TagService {
	void insertTag(TagDto tagDto);
	void updateTag(TagDto tagDto);
	void deleteTag(int tagId);
	TagDto selectTagById(int tagId);
	List<TagDto> selectAllTags();
}