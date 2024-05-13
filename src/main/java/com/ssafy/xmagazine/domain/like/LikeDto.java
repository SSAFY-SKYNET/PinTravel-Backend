package com.ssafy.xmagazine.domain.like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "LikeDto")
public class LikeDto {
	@Schema(name = "likeId", description = "좋아요 ID")
	private int likeId;

	@Schema(name = "pinId", description = "핀 ID")
	private int pinId;

	@Schema(name = "userId", description = "사용자 ID")
	private int userId;

	@Schema(name = "createdAt", description = "좋아요 생성 시간")
	private LocalDateTime createdAt;
}