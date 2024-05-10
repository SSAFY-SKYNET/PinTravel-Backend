package com.ssafy.xmagazine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.domain.magazine.MagazineDto;

@Mapper
public interface MagazineMapper {

	@Select("SELECT * FROM magazine")
	List<MagazineDto> selectMagazine();

	@Select("SELECT * FROM magazine WHERE id = #{id}")
	MagazineDto selectMagazineById(int id);

	@Insert("INSERT INTO magazine (user_id, title, description) VALUES (#{userId}, #{title}, #{description})")
	void insertMagazine(MagazineDto magazineDto);

	@Update("UPDATE magazine SET title = #{title}, description = #{description} WHERE id = #{id}")
	void updateMagazine(MagazineDto magazineDto);

	@Delete("DELETE FROM magazine WHERE id = #{id}")
	void deleteMagazine(int id);
}
