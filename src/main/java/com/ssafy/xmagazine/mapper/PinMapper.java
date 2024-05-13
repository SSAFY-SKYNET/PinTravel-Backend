package com.ssafy.xmagazine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ssafy.xmagazine.domain.pin.PinDto;

@Mapper
public interface PinMapper {

	@Select("SELECT * FROM pins WHERE pin_id = #{pinId}")
	PinDto selectPinById(int pinId);

	@Select("SELECT p.* FROM pins p	JOIN likes l ON p.pin_id = l.pin_id	GROUP BY p.pin_id ORDER BY COUNT(l.like_id) DESC")
	List<PinDto> selectPinByLikeCount();

	@Select("SELECT p.* FROM pins p JOIN likes l ON p.pin_id = l.pin_id GROUP BY p.pin_id ORDER BY COUNT(l.like_id) DESC, p.pin_id DESC LIMIT #{limit} OFFSET #{offset}")
	List<PinDto> selectPinByLikeCountAndPage(@Param("offset") int offset, @Param("limit") int limit);

	@Select("SELECT * FROM pins WHERE user_id = #{userId}")
	List<PinDto> selectPinByUserId(int userId);

	@Select("SELECT * FROM pins WHERE user_id = #{userId} ORDER BY pin_id DESC LIMIT #{limit} OFFSET #{offset}")
	List<PinDto> selectPinByUserIdAndPage(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

	@Select("SELECT p.* FROM pins p	JOIN pintags pt ON p.pin_id = pt.pin_id JOIN tags t ON pt.tag_id = #{tagId}")
	List<PinDto> selectPinByTag(int tagId);

	@Select("SELECT p.* FROM pins p JOIN pin_tags pt ON p.pin_id = pt.pin_id WHERE pt.tag_id = #{tagId} ORDER BY p.pin_id DESC LIMIT #{limit} OFFSET #{offset}")
	List<PinDto> selectPinByTagAndPage(@Param("tagId") int tagId, @Param("offset") int offset, @Param("limit") int limit);

	@Select("SELECT p.* FROM pins p	JOIN pinboards pb ON p.pin_id = pb.pin_id JOIN boards b ON pb.board_id = b.board_id WHERE b.board_id = #{boardId}")
	List<PinDto> selectPinByBoard(int boardId);

	@Select("SELECT p.* FROM pins p JOIN pin_boards pb ON p.pin_id = pb.pin_id WHERE pb.board_id = #{boardId} ORDER BY p.pin_id DESC LIMIT #{limit} OFFSET #{offset}")
	List<PinDto> selectPinByBoardAndPage(@Param("boardId") int boardId, @Param("offset") int offset, @Param("limit") int limit);

	@Insert("INSERT INTO pins (user_id, image_url, description, address, latitude, longitude) VALUES (#{userId}, #{image_url}, #{description}, #{address}, #{latitude}, #{longitude})")
	void insertPin(PinDto pinDto);

	@Update("UPDATE pins SET description = #{description} WHERE pin_id = #{id}")
	void updatePin(PinDto pinDto);

	// @Delete("DELETE FROM pins WHERE pin_id = #{id}")
	// void deletePin(int id);
}
