package com.ssafy.xmagazine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "CommentDto")
public class CommentDto {
    @Schema(name = "ID", description = "댓글 고유 ID")
    private int id;
    @Schema(name = "magazineId", description = "잡지 고유 ID")
    private int magazineId;
    @Schema(name = "userId", description = "댓글 작성 유저 고유 ID")
    private int userId;
    @Schema(name = "content", description = "댓글 내용")
    private String content;
    @Schema(name = "createdAt", description = "댓글 생성 날짜")
    private String createdAt;
}