package com.ssafy.xmagazine.domain.page;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PageMapper {

	@Select("SELECT * FROM page WHERE magazine_id = #{magazineId}")
	List<PageDto> selectPageByMagazineId(int magazineId);

	@Select("SELECT * FROM page WHERE id = #{id}")
	PageDto selectPageById(int id);

	@Insert("INSERT INTO page (magazine_id, page_number) VALUES (#{magazineId}, #{pageNumber})")
	void insertPage(PageDto pageDto);

	@Update("UPDATE page SET page_number = #{pageNumber} WHERE id = #{id}")
	void updatePage(PageDto pageDto);

	@Delete("DELETE FROM page WHERE id = #{id}")
	void deletePage(int id);
}