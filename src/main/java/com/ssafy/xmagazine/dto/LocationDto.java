package com.ssafy.xmagazine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "LocationDto")
public class LocationDto {
    @Schema(name = "ID", description = "위치 고유 ID")
    private int id;
    @Schema(name = "magazineId", description = "잡지 고유 ID")
    private int magazineId;
    @Schema(name = "name", description = "위치 이름")
    private String name;
    @Schema(name = "address", description = "위치 주소")
    private String address;
    @Schema(name = "latitude", description = "위치 위도")
    private double latitude;
    @Schema(name = "longitude", description = "위치 경도")
    private double longitude;
    @Schema(name = "createdAt", description = "위치 생성 날짜")
    private String createdAt;
}