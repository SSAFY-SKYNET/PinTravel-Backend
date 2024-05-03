package com.ssafy.xmagazine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "MagazineDto")
public class MagazineDto {
    @Schema(name = "ID", description = "매거진 고유 ID")
    private int id;
    @Schema(name = "userId", description = "잡지 작성 유저 고유 ID")
    private int userId;
    @Schema(name = "title", description = "잡지 이름")
    private String title;
    @Schema(name = "description", description = "잡지 설명")
    private String description;
    @Schema(name = "createdAt", description = "잡지 생성 날짜")
    private String createdAt;
}