package com.ssafy.xmagazine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.dto.MagazineDto;

@Service
public class MagazineServiceImpl implements MagazineService {
	@Override
	public List<MagazineDto> selectMagazine() {
		return List.of();
	}

	@Override
	public MagazineDto selectMagazineById(int id) {
		return null;
	}

	@Override
	public void insertMagazine(MagazineDto magazineDto) {

	}

	@Override
	public void updateMagazine(MagazineDto magazineDto) {

	}

	@Override
	public void deleteMagazine(int id) {

	}
}
