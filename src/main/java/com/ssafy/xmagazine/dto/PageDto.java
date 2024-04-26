package com.ssafy.xmagazine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PageDto")
public class PageDto {
    @Schema(name = "ID", description = "페이지 고유 ID")
    private int id;
    @Schema(name = "magazineId", description = "잡지 고유 ID")
    private int magazineId;
    @Schema(name = "pageNumber", description = "페이지 번호")
    private int pageNumber;
    @Schema(name = "createdAt", description = "페이지 생성 날짜")
    private String createdAt;
}