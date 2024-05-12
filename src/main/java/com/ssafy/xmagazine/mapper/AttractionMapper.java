package com.ssafy.xmagazine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.domain.attraction.AttractionDto;

@Mapper
public interface AttractionMapper {

    @Select("SELECT * FROM attraction_info")
    List<AttractionDto> findAllAttractions();

    @Select("SELECT * FROM attraction_info WHERE content_id = #{content_id}")
    AttractionDto findAttractionById(int content_id);

    @Insert("INSERT INTO attraction_info (content_type_id, title, addr1, addr2, zipcode, tel, first_image, first_image2, readcount, sido_code, gugun_code, latitude, longitude, mlevel) VALUES (#{content_type_id}, #{title}, #{addr1}, #{addr2}, #{zipcode}, #{tel}, #{first_image}, #{first_image2}, #{readcount}, #{sido_code}, #{gugun_code}, #{latitude}, #{longitude}, #{mlevel})")
    void saveAttraction(AttractionDto attractionDto);

    @Update("UPDATE attraction_info SET content_type_id = #{content_type_id}, title = #{title}, addr1 = #{addr1}, addr2 = #{addr2}, zipcode = #{zipcode}, tel = #{tel}, first_image = #{first_image}, first_image2 = #{first_image2}, readcount = #{readcount}, sido_code = #{sido_code}, gugun_code = #{gugun_code}, latitude = #{latitude}, longitude = #{longitude}, mlevel = #{mlevel} WHERE content_id = #{content_id}")
    void updateAttraction(AttractionDto attractionDto);

    @Delete("DELETE FROM attraction_info WHERE content_id = #{content_id}")
    void deleteAttraction(int content_id);

    @Select("SELECT * FROM attraction_info WHERE first_image != '' LIMIT #{page}, #{limit}")
    List<AttractionDto> findAllAttractionsByPage(int page, int limit);
}
