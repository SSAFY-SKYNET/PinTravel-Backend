package com.ssafy.xmagazine.domain.attraction;

import java.util.List;

public interface AttractionService {
    List<AttractionDto> findAllAttractions();

    AttractionDto findAttractionById(int id);

    void saveAttraction(AttractionDto attractionDto);

    void updateAttraction(AttractionDto attractionDto);

    void deleteAttraction(int id);

    List<AttractionDto> findAllAttractionsByPage(int page, int limit);
}
