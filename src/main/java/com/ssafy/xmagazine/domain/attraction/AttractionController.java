package com.ssafy.xmagazine.domain.attraction;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/attraction")
@Tag(name = "AttractionController", description = "관광지 관련 API")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionServiceImpl attractionService;

    @GetMapping
    @Operation(summary = "모든 관광지 정보 조회", description = "모든 관광지 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 관광지 정보를 조회했습니다.")
    public ResponseEntity<List<AttractionDto>> findAllAttractions() {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findAllAttractions());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 관광지 정보 조회", description = "ID로 관광지 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 관광지 정보를 조회했습니다.")
    public ResponseEntity<AttractionDto> getAttractionById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findAttractionById(id));
    }

    @PostMapping
    @Operation(summary = "새로운 관광지 추가", description = "새로운 관광지를 추가합니다.")
    @ApiResponse(responseCode = "201", description = "관광지가 성공적으로 추가되었습니다.")
    public ResponseEntity<Void> createAttraction(@RequestBody AttractionDto attractionDto) {
        attractionService.saveAttraction(attractionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "기존 관광지 정보 업데이트", description = "기존 관광지 정보를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "관광지 정보가 성공적으로 업데이트되었습니다.")
    public ResponseEntity<Void> updateAttraction(@PathVariable int id, @RequestBody AttractionDto attractionDto) {
        attractionDto.setContent_id(id);
        attractionService.updateAttraction(attractionDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "관광지 정보 삭제", description = "관광지 정보를 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "관광지 정보가 성공적으로 삭제되었습니다.")
    public ResponseEntity<Void> deleteAttraction(@PathVariable int id) {
        attractionService.deleteAttraction(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/page")
    @Operation(summary = "페이지 별 관광지 정보 조회", description = "페이지 별 관광지 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 관광지 정보를 조회했습니다.")
    public ResponseEntity<List<AttractionDto>> findAllAttractionsByPage(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        int offset = (page - 1) * limit;
        return ResponseEntity.status(HttpStatus.OK).body(attractionService.findAllAttractionsByPage(offset, limit));
    }
}
