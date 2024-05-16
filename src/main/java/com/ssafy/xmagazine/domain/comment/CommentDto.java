package com.ssafy.xmagazine.domain.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "CommentDto")
public class CommentDto {
    @Schema(name = "comment_id", description = "댓글 고유 ID")
    private int commentId;
    @Schema(name = "pinId", description = "핀 고유 ID")
    private int pinId;
    @Schema(name = "userId", description = "댓글 작성 유저 고유 ID")
    private int userId;
    @Schema(name = "text", description = "댓글 내용")
    private String text;
    @Schema(name = "createdAt", description = "댓글 생성 날짜")
    private String createdAt;
}