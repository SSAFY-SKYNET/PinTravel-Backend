package com.ssafy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.dto.MagazineDto;

@Service
public class MagazineServiceImpl implements MagazineService{
	@Override
	public List<MagazineDto> getMagazineList() {
		return List.of();
	}

	@Override
	public MagazineDto getMagazine(int id) {
		return null;
	}

	@Override
	public void createMagazine(MagazineDto magazineDto) {

	}

	@Override
	public void updateMagazine(MagazineDto magazineDto) {

	}

	@Override
	public void deleteMagazine(int id) {

	}
}
