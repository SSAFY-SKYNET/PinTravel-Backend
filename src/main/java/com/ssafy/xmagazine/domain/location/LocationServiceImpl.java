package com.ssafy.xmagazine.domain.location;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationServiceImpl implements LocationService {

	private final LocationMapper locationMapper;

	@Override
	public List<LocationDto> selectLocationByMagazineId(int magazineId) {
		return locationMapper.selectLocationByMagazineId(magazineId);
	}

	@Override
	public LocationDto selectLocationById(int id) {
		return locationMapper.selectLocationById(id);
	}

	@Override
	public void insertLocation(LocationDto locationDto) {
		locationMapper.insertLocation(locationDto);
	}

	@Override
	public void updateLocation(LocationDto locationDto) {
		locationMapper.updateLocation(locationDto);
	}

	@Override
	public void deleteLocation(int id) {
		locationMapper.deleteLocation(id);
	}
}