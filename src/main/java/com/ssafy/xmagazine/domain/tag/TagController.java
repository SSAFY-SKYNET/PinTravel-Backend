package com.ssafy.xmagazine.domain.tag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Tag(name = "Tag", description = "Tag API")
public class TagController {
	private final TagService tagService;

	public TagController(TagService tagService) {
		this.tagService = tagService;
	}

	@PostMapping
	@Operation(summary = "태그 추가", description = "새로운 태그를 추가합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Void> insertTag(@RequestBody TagDto tagDto) {
		tagService.insertTag(tagDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{tagId}")
	@Operation(summary = "태그 수정", description = "태그 정보를 수정합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> updateTag(@PathVariable int tagId, @RequestBody TagDto tagDto) {
		tagDto.setTagId(tagId);
		tagService.updateTag(tagDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{tagId}")
	@Operation(summary = "태그 삭제", description = "태그를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> deleteTag(@PathVariable int tagId) {
		tagService.deleteTag(tagId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/{tagId}")
	@Operation(summary = "태그 조회", description = "태그 정보를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<TagDto> selectTagById(@PathVariable int tagId) {
		TagDto tagDto = tagService.selectTagById(tagId);
		return ResponseEntity.ok(tagDto);
	}

	@GetMapping
	@Operation(summary = "모든 태그 조회", description = "모든 태그 정보를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<TagDto>> selectAllTags() {
		List<TagDto> tagDtoList = tagService.selectAllTags();
		return ResponseEntity.ok(tagDtoList);
	}

	@GetMapping("/search")
	@Operation(summary = "관련된 태그 모두 조회", description = "입력된 태그들과 관련된 태그 정보를 모두 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<TagDto>> selectTagsByMultipleInputs(@RequestBody List<String> inputs,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		List<TagDto> tagDtoList = tagService.selectTagsByMultipleInputs(inputs, pageSize, pageNum);
		return ResponseEntity.ok(tagDtoList);
	}

	@GetMapping("/search/{input}")
	@Operation(summary = "관련된 태그 모두 조회", description = "입력된 태그들과 관련된 태그 정보를 모두 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<TagDto>> selectTagsByInput(@PathVariable String input) {
		List<TagDto> tagDtoList = tagService.selectTagsByInput(input);
		return ResponseEntity.ok(tagDtoList);
	}
}
