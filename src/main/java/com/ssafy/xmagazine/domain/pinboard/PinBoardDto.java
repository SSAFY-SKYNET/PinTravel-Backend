package com.ssafy.xmagazine.domain.pinboard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PinBoardDto")
public class PinBoardDto {
	@Schema(name = "pinId", description = "핀 ID")
	private int pinId;

	@Schema(name = "boardId", description = "보드 ID")
	private int boardId;
}