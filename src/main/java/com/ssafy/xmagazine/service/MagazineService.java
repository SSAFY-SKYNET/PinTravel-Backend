package com.ssafy.xmagazine.service;

import java.util.List;

import com.ssafy.xmagazine.dto.MagazineDto;

public interface MagazineService {
	List<MagazineDto> selectMagazine();

	MagazineDto selectMagazineById(int id);

	void insertMagazine(MagazineDto magazineDto);

	void updateMagazine(MagazineDto magazineDto);

	void deleteMagazine(int id);
}
