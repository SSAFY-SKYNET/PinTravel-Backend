package com.ssafy.xmagazine.domain.tag;

import java.util.List;

public interface TagService {
	void insertTag(TagDto tagDto);

	void updateTag(TagDto tagDto);

	void deleteTag(int tagId);

	TagDto selectTagById(int tagId);

	List<TagDto> selectAllTags();

	List<TagDto> selectTagsByMultipleInputs(List<String> inputs, int pageSize, int pageNum);

	List<TagDto> selectTagsByInput(String input);

	List<TagDto> selectTagsByIds(List<Integer> tagIds);
}
