package com.ssafy.xmagazine.domain.attraction;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.xmagazine.mapper.AttractionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionMapper attractionMapper;

    @Override
    public List<AttractionDto> findAllAttractions() {
        return attractionMapper.findAllAttractions();
    }

    @Override
    public AttractionDto findAttractionById(int id) {
        return attractionMapper.findAttractionById(id);
    }

    @Override
    public void saveAttraction(AttractionDto attractionDto) {
        attractionMapper.saveAttraction(attractionDto);
    }

    @Override
    public void updateAttraction(AttractionDto attractionDto) {
        attractionMapper.updateAttraction(attractionDto);
    }

    @Override
    public void deleteAttraction(int id) {
        attractionMapper.deleteAttraction(id);
    }

    @Override
    public List<AttractionDto> findAllAttractionsByPage(int page, int limit) {
        return attractionMapper.findAllAttractionsByPage(page, limit);
    }
}
