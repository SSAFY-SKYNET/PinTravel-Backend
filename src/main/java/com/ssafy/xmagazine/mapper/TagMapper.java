package com.ssafy.xmagazine.mapper;

import com.ssafy.xmagazine.domain.tag.TagDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {
	@Insert("insert into tags (name) values (#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "tagId")
	void insertTag(TagDto tagDto);

	@Update("update tags set name = #{name} where tag_id = #{tagId}")
	void updateTag(TagDto tagDto);

	@Delete("delete from tags where tag_id = #{tagId}")
	void deleteTag(int tagId);

	@Select("select * from tags where tag_id = #{tagId}")
	TagDto selectTagById(int tagId);

	@Select("select * from tags")
	List<TagDto> selectAllTags();

	@Select("<script>" +
			"select distinct * from tags " +
			"where " +
			"<foreach item='item' collection='inputs' open='' separator=' or ' close=''>" +
			"name like concat('%', #{item}, '%')" +
			"</foreach>" +
			" limit #{pageSize} offset #{offset}" +
			"</script>")
	List<TagDto> selectTagsByMultipleInputs(@Param("inputs") List<String> inputs, @Param("pageSize") int pageSize,
			@Param("pageNum") int pageNum, @Param("offset") int offset);

	@Select("select * from tags " +
			"where name like concat('%', #{input}, '%') " +
			"order by char_length(name), name " +
			"limit 5")
	List<TagDto> selectTagsByInput(@Param("input") String input);

	@Select("<script>" +
			"select * from tags " +
			"where tag_id in " +
			"<foreach item='id' collection='tagIds' open='(' separator=',' close=')'>" +
			"#{id}" +
			"</foreach> " +
			"order by field(tag_id, " +
			"<foreach item='id' collection='tagIds' separator=','>" +
			"#{id}" +
			"</foreach>)" +
			"</script>")
	List<TagDto> selectTagsByIds(@Param("tagIds") List<Integer> tagIds);
}