package com.ssafy.xmagazine.domain.magazine;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping
	@Operation(summary = "잡지 등록", description = "새로운 잡지를 등록합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> insertMagazine(@RequestBody MagazineDto magazineDto) {
		magazineService.insertMagazine(magazineDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "잡지 수정", description = "ID로 잡지를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> updateMagazine(@PathVariable int id, @RequestBody MagazineDto magazineDto) {
		magazineDto.setId(id);
		magazineService.updateMagazine(magazineDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "잡지 삭제", description = "ID로 잡지를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> deleteMagazine(@PathVariable int id) {
		magazineService.deleteMagazine(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}