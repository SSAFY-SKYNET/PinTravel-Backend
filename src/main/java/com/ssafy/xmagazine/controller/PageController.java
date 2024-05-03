package com.ssafy.xmagazine.controller;

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

import com.ssafy.xmagazine.dto.PageDto;
import com.ssafy.xmagazine.service.PageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/page")
@Tag(name = "Page", description = "Page API")
@RequiredArgsConstructor
public class PageController {
	private final PageService pageService;

	@GetMapping("/magazine/{magazineId}")
	@Operation(summary = "잡지 ID로 페이지 조회", description = "잡지 ID로 페이지를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<PageDto>> selectPageByMagazineId(@PathVariable int magazineId) {
		return ResponseEntity.status(HttpStatus.OK).body(pageService.selectPageByMagazineId(magazineId));
	}

	@GetMapping("/{id}")
	@Operation(summary = "ID로 페이지 조회", description = "ID로 페이지를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<PageDto> selectPageById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(pageService.selectPageById(id));
	}

	@PostMapping
	@Operation(summary = "페이지 등록", description = "새로운 페이지를 등록합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> insertPage(@RequestBody PageDto pageDto) {
		pageService.insertPage(pageDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "페이지 수정", description = "ID로 페이지를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> updatePage(@PathVariable int id, @RequestBody PageDto pageDto) {
		pageDto.setId(id);
		pageService.updatePage(pageDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "페이지 삭제", description = "ID로 페이지를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> deletePage(@PathVariable int id) {
		pageService.deletePage(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}