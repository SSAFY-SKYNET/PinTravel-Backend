package com.ssafy.xmagazine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private int id;
    private int magazineId;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String createdAt;
}
