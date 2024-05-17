package com.ssafy.xmagazine.domain.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "BoardDto")
public class BoardDto {
    @Schema(name = "boardId", description = "게시판 고유 ID")
    private int boardId;

    @Schema(name = "userId", description = "게시판 작성자 ID")
    private int userId;

    @Schema(name = "thumbnail", description = "Board 썸네일")
    private String thumbnail;

    @Schema(name = "title", description = "게시판 제목")
    private String title;

    @Schema(name = "description", description = "게시판 설명")
    private String description;

    @Schema(name = "isPrivate", description = "게시판 공개 여부")
    private boolean isPrivate;

    @Schema(name = "createdAt", description = "게시판 생성 날짜")
    private LocalDateTime createdAt;
}