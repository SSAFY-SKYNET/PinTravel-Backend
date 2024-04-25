package com.ssafy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagazineDto {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String createdAt;
}
