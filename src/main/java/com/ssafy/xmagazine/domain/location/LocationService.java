package com.ssafy.xmagazine.domain.location;

import java.util.List;

public interface LocationService {

	List<LocationDto> selectLocationByMagazineId(int magazineId);

	LocationDto selectLocationById(int id);

	void insertLocation(LocationDto locationDto);

	void updateLocation(LocationDto locationDto);

	void deleteLocation(int id);
}