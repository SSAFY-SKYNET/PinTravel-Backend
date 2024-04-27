package com.ssafy.xmagazine.service;

import java.util.List;

import com.ssafy.xmagazine.dto.LocationDto;

public interface LocationService {

	List<LocationDto> selectLocationByMagazineId(int magazineId);

	LocationDto selectLocationById(int id);

	void insertLocation(LocationDto locationDto);

	void updateLocation(LocationDto locationDto);

	void deleteLocation(int id);
}