package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.tag.TagDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {
	@Insert("INSERT INTO Tags (name) VALUES (#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "tagId")
	void insertTag(TagDto tagDto);

	@Update("UPDATE Tags SET name = #{name} WHERE tag_id = #{tagId}")
	void updateTag(TagDto tagDto);

	@Delete("DELETE FROM Tags WHERE tag_id = #{tagId}")
	void deleteTag(int tagId);

	@Select("SELECT * FROM Tags WHERE tag_id = #{tagId}")
	TagDto selectTagById(int tagId);

	@Select("SELECT * FROM Tags")
	List<TagDto> selectAllTags();
}