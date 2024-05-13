package com.ssafy.xmagazine.domain.pintag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pintags")
@Tag(name = "PinTag", description = "PinTag API")
public class PinTagController {
	private final PinTagService pinTagService;

	public PinTagController(PinTagService pinTagService) {
		this.pinTagService = pinTagService;
	}

	@PostMapping
	@Operation(summary = "핀-태그 관계 추가", description = "핀과 태그의 관계를 추가합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Void> insertPinTag(@RequestBody PinTagDto pinTagDto) {
		pinTagService.insertPinTag(pinTagDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{pinId}/{tagId}")
	@Operation(summary = "핀-태그 관계 삭제", description = "핀과 태그의 관계를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> deletePinTag(@PathVariable int pinId, @PathVariable int tagId) {
		pinTagService.deletePinTag(pinId, tagId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/tag/{tagId}")
	@Operation(summary = "태그의 핀 ID 목록 조회", description = "특정 태그에 속한 핀의 ID 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Integer>> selectPinIdsByTagId(@PathVariable int tagId) {
		List<Integer> pinIds = pinTagService.selectPinIdsByTagId(tagId);
		return ResponseEntity.ok(pinIds);
	}

	@GetMapping("/pin/{pinId}")
	@Operation(summary = "핀의 태그 ID 목록 조회", description = "특정 핀에 속한 태그의 ID 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Integer>> selectTagIdsByPinId(@PathVariable int pinId) {
		List<Integer> tagIds = pinTagService.selectTagIdsByPinId(pinId);
		return ResponseEntity.ok(tagIds);
	}
}