package com.ssafy.xmagazine.domain.magazine;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
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
