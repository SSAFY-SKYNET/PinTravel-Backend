package com.ssafy.xmagazine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    private int id;
    private int magazineId;
    private int pageNumber;
    private String createdAt;
}
