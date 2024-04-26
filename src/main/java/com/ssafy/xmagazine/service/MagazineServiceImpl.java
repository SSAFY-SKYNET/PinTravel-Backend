package com.ssafy.xmagazine.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.dto.MagazineDto;
import com.ssafy.xmagazine.repository.MagazineMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MagazineServiceImpl implements MagazineService {

	private final MagazineMapper magazineMapper;

	@Override
	public List<MagazineDto> selectMagazine() {
		return magazineMapper.selectMagazine();
	}

	@Override
	public MagazineDto selectMagazineById(int id) {
		return magazineMapper.selectMagazineById(id);
	}

	@Override
	public void insertMagazine(MagazineDto magazineDto) {
		magazineMapper.insertMagazine(magazineDto);
	}

	@Override
	public void updateMagazine(MagazineDto magazineDto) {
		magazineMapper.updateMagazine(magazineDto);
	}

	@Override
	public void deleteMagazine(int id) {
		magazineMapper.deleteMagazine(id);
	}
}
