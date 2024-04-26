package com.ssafy.xmagazine.service;

import java.util.List;

import com.ssafy.xmagazine.dto.MagazineDto;

public interface MagazineService {
	List<MagazineDto> getMagazineList();

	MagazineDto getMagazine(int id);

	void createMagazine(MagazineDto magazineDto);

	void updateMagazine(MagazineDto magazineDto);

	void deleteMagazine(int id);
}
