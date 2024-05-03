package com.ssafy.xmagazine.domain.location;

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
@RequestMapping("/location")
@Tag(name = "Location", description = "Location API")
@RequiredArgsConstructor
public class LocationController {
	private final LocationService locationService;

	@GetMapping("/magazine/{magazineId}")
	@Operation(summary = "잡지 ID로 위치 조회", description = "잡지 ID로 위치를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<LocationDto>> selectLocationByMagazineId(@PathVariable int magazineId) {
		return ResponseEntity.status(HttpStatus.OK).body(locationService.selectLocationByMagazineId(magazineId));
	}

	@GetMapping("/{id}")
	@Operation(summary = "ID로 위치 조회", description = "ID로 위치를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<LocationDto> selectLocationById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(locationService.selectLocationById(id));
	}

	@PostMapping
	@Operation(summary = "위치 등록", description = "새로운 위치를 등록합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> insertLocation(@RequestBody LocationDto locationDto) {
		locationService.insertLocation(locationDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "위치 수정", description = "ID로 위치를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> updateLocation(@PathVariable int id, @RequestBody LocationDto locationDto) {
		locationDto.setId(id);
		locationService.updateLocation(locationDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "위치 삭제", description = "ID로 위치를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
		locationService.deleteLocation(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}