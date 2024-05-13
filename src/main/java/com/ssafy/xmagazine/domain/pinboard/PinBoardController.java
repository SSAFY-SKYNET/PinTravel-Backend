package com.ssafy.xmagazine.domain.pinboard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pinboards")
@Tag(name = "PinBoard", description = "PinBoard API")
public class PinBoardController {
	private final PinBoardService pinBoardService;

	public PinBoardController(PinBoardService pinBoardService) {
		this.pinBoardService = pinBoardService;
	}

	@PostMapping
	@Operation(summary = "핀-보드 관계 추가", description = "핀과 보드의 관계를 추가합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Void> insertPinBoard(@RequestBody PinBoardDto pinBoardDto) {
		pinBoardService.insertPinBoard(pinBoardDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{pinId}/{boardId}")
	@Operation(summary = "핀-보드 관계 삭제", description = "핀과 보드의 관계를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> deletePinBoard(@PathVariable int pinId, @PathVariable int boardId) {
		pinBoardService.deletePinBoard(pinId, boardId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/board/{boardId}")
	@Operation(summary = "보드의 핀 ID 목록 조회", description = "특정 보드에 속한 핀의 ID 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Integer>> selectPinIdsByBoardId(@PathVariable int boardId) {
		List<Integer> pinIds = pinBoardService.selectPinIdsByBoardId(boardId);
		return ResponseEntity.ok(pinIds);
	}

	@GetMapping("/pin/{pinId}")
	@Operation(summary = "핀이 속한 보드 ID 목록 조회", description = "특정 핀이 속한 보드의 ID 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<Integer>> selectBoardIdsByPinId(@PathVariable int pinId) {
		List<Integer> boardIds = pinBoardService.selectBoardIdsByPinId(pinId);
		return ResponseEntity.ok(boardIds);
	}
}