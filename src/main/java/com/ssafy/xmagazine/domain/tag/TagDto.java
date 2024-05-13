package com.ssafy.xmagazine.domain.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "TagDto")
public class TagDto {
	@Schema(name = "tagId", description = "태그 ID")
	private int tagId;

	@Schema(name = "name", description = "태그 이름")
	private String name;
}