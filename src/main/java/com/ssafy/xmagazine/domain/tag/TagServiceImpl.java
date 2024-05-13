package com.ssafy.xmagazine.domain.tag;

import com.ssafy.xmagazine.mapper.TagMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

	private final TagMapper tagMapper;

	public TagServiceImpl(TagMapper tagMapper) {
		this.tagMapper = tagMapper;
	}

	@Override
	public void insertTag(TagDto tagDto) {
		tagMapper.insertTag(tagDto);
	}

	@Override
	public void updateTag(TagDto tagDto) {
		tagMapper.updateTag(tagDto);
	}

	@Override
	public void deleteTag(int tagId) {
		tagMapper.deleteTag(tagId);
	}

	@Override
	public TagDto selectTagById(int tagId) {
		return tagMapper.selectTagById(tagId);
	}

	@Override
	public List<TagDto> selectAllTags() {
		return tagMapper.selectAllTags();
	}
}