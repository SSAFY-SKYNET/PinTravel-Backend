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

import com.ssafy.xmagazine.dto.ContentDto;
import com.ssafy.xmagazine.service.ContentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/content")
@Tag(name = "Content", description = "Content API")
@RequiredArgsConstructor
public class ContentController {
	private final ContentService contentService;

	@GetMapping("/magazine/{magazineId}")
	@Operation(summary = "잡지 ID로 컨텐츠 조회", description = "잡지 ID로 컨텐츠를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<ContentDto>> selectContentByMagazineId(@PathVariable int magazineId) {
		return ResponseEntity.status(HttpStatus.OK).body(contentService.selectContentByMagazineId(magazineId));
	}

	@GetMapping("/page/{pageId}")
	@Operation(summary = "페이지 ID로 컨텐츠 조회", description = "페이지 ID로 컨텐츠를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<ContentDto>> selectContentByPageId(@PathVariable int pageId) {
		return ResponseEntity.status(HttpStatus.OK).body(contentService.selectContentByPageId(pageId));
	}

	@GetMapping("/{id}")
	@Operation(summary = "ID로 컨텐츠 조회", description = "ID로 컨텐츠를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<ContentDto> selectContentById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(contentService.selectContentById(id));
	}

	@PostMapping
	@Operation(summary = "컨텐츠 등록", description = "새로운 컨텐츠를 등록합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> insertContent(@RequestBody ContentDto contentDto) {
		contentService.insertContent(contentDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "컨텐츠 수정", description = "ID로 컨텐츠를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> updateContent(@PathVariable int id, @RequestBody ContentDto contentDto) {
		contentDto.setId(id);
		contentService.updateContent(contentDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "컨텐츠 삭제", description = "ID로 컨텐츠를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> deleteContent(@PathVariable int id) {
		contentService.deleteContent(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}