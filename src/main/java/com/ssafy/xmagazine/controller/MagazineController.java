package com.ssafy.xmagazine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.xmagazine.dto.MagazineDto;
import com.ssafy.xmagazine.service.MagazineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/magazine")
@Tag(name = "Magazine", description = "Magazine API")
@RequiredArgsConstructor
public class MagazineController {
	private final MagazineService magazineService;

	@GetMapping
	@Operation(summary = "잡지목록 조회", description = "ID로 잡지 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<MagazineDto>> selectMagazine() {
		return ResponseEntity.status(HttpStatus.OK).body(magazineService.selectMagazine());
	}

	@GetMapping("/{id}")
	@Operation(summary = "ID로 잡지 조회", description = "ID로 잡지를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<MagazineDto> selectMagazineById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(magazineService.selectMagazineById(id));
	}
}
