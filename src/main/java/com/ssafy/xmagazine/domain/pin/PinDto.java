package com.ssafy.xmagazine.domain.pin;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PinDto")
public class PinDto {
	@Schema(name = "pinId", description = "핀 고유 ID")
	private int pinId;

	@Schema(name = "userId", description = "핀 작성 유저 고유 ID")
	private int userId;

	@Schema(name = "title", description = "핀 제목")
	private String title;

	@Schema(name = "imageUrl", description = "핀 이미지 URL")
	private String imageUrl;

	@Schema(name = "description", description = "핀 설명")
	private String description;

	@Schema(name = "address", description = "핀 주소")
	private String address;

	@Schema(name = "latitude", description = "핀 위도")
	private double latitude;

	@Schema(name = "longitude", description = "핀 경도")
	private double longitude;

	@Schema(name = "createdAt", description = "핀 생성 날짜")
	private LocalDateTime createdAt;

	@Schema(name = "isDeleted", description = "핀 삭제 여부")
	private boolean isDeleted;
}