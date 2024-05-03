package com.ssafy.xmagazine.domain.magazine;

import java.util.List;

public interface MagazineService {
	List<MagazineDto> selectMagazine();

	MagazineDto selectMagazineById(int id);

	void insertMagazine(MagazineDto magazineDto);

	void updateMagazine(MagazineDto magazineDto);

	void deleteMagazine(int id);
}
