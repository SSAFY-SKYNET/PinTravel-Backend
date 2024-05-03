package com.ssafy.xmagazine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "ContentDto")
public class ContentDto {
    @Schema(name = "ID", description = "컨텐츠 고유 ID")
    private int id;
    @Schema(name = "pageId", description = "페이지 고유 ID")
    private int pageId;
    @Schema(name = "magazineId", description = "잡지 고유 ID")
    private int magazineId;
    @Schema(name = "contentType", description = "컨텐츠 타입")
    private String contentType;
    @Schema(name = "content", description = "컨텐츠 내용")
    private String content;
    @Schema(name = "imgUrl", description = "컨텐츠 이미지 URL")
    private String imgUrl;
    @Schema(name = "createdAt", description = "컨텐츠 생성 날짜")
    private String createdAt;
}