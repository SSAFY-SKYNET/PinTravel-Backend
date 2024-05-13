package com.ssafy.xmagazine.domain.follower;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "FollowerDto")
public class FollowerDto {
	@Schema(name = "followerId", description = "팔로워 ID")
	private int followerId;

	@Schema(name = "followedId", description = "팔로잉 ID")
	private int followedId;

	@Schema(name = "createdAt", description = "팔로우 시작 날짜")
	private LocalDateTime createdAt;
}