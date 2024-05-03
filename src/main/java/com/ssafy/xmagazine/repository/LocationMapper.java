package com.ssafy.xmagazine.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.dto.LocationDto;

@Mapper
public interface LocationMapper {

	@Select("SELECT * FROM location WHERE magazine_id = #{magazineId}")
	List<LocationDto> selectLocationByMagazineId(int magazineId);

	@Select("SELECT * FROM location WHERE id = #{id}")
	LocationDto selectLocationById(int id);

	@Insert("INSERT INTO location (magazine_id, name, address, latitude, longitude) VALUES (#{magazineId}, #{name}, #{address}, #{latitude}, #{longitude})")
	void insertLocation(LocationDto locationDto);

	@Update("UPDATE location SET name = #{name}, address = #{address}, latitude = #{latitude}, longitude = #{longitude} WHERE id = #{id}")
	void updateLocation(LocationDto locationDto);

	@Delete("DELETE FROM location WHERE id = #{id}")
	void deleteLocation(int id);
}