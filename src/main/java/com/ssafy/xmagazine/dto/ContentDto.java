package com.ssafy.xmagazine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    private int id;
    private int pageId;
    private int magazineId;
    private String contentType;
    private String content;
    private String imgUrl;
    private String createdAt;
}
