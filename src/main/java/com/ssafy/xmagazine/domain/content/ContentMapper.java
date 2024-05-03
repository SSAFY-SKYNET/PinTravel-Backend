package com.ssafy.xmagazine.domain.content;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ContentMapper {

	@Select("SELECT * FROM content WHERE magazine_id = #{magazineId}")
	List<ContentDto> selectContentByMagazineId(int magazineId);

	@Select("SELECT * FROM content WHERE page_id = #{pageId}")
	List<ContentDto> selectContentByPageId(int pageId);

	@Select("SELECT * FROM content WHERE id = #{id}")
	ContentDto selectContentById(int id);

	@Insert("INSERT INTO content (page_id, magazine_id, content_type, content, img_url) VALUES (#{pageId}, #{magazineId}, #{contentType}, #{content}, #{imgUrl})")
	void insertContent(ContentDto contentDto);

	@Update("UPDATE content SET content_type = #{contentType}, content = #{content}, img_url = #{imgUrl} WHERE id = #{id}")
	void updateContent(ContentDto contentDto);

	@Delete("DELETE FROM content WHERE id = #{id}")
	void deleteContent(int id);
}