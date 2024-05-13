package com.ssafy.xmagazine.domain.pintag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PinTagDto")
public class PinTagDto {
	@Schema(name = "pinId", description = "핀 ID")
	private int pinId;

	@Schema(name = "tagId", description = "태그 ID")
	private int tagId;
}