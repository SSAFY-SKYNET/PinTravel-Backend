package com.ssafy.xmagazine.domain.pinboard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.ssafy.xmagazine.exception.UnAuthorizedException;
import com.ssafy.xmagazine.util.JWTUtil;

@RestController
@RequestMapping("/pinboards")
@Tag(name = "PinBoard", description = "PinBoard API")
public class PinBoardController {
	private final PinBoardService pinBoardService;
	private final JWTUtil jwtUtil;

	public PinBoardController(PinBoardService pinBoardService, JWTUtil jwtUtil) {
		this.pinBoardService = pinBoardService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping
	@Operation(summary = "핀-보드 관계 추가", description = "핀과 보드의 관계를 추가합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> insertPinBoard(@RequestBody PinBoardDto pinBoardDto,
		@RequestHeader("Authorization") String token) {
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);
			pinBoardDto.setUserId(userId);

			pinBoardService.insertPinBoard(pinBoardDto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{pinId}/{boardId}")
	@Operation(summary = "핀-보드 관계 삭제", description = "핀과 보드의 관계를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> deletePinBoard(@PathVariable int pinId, @PathVariable int boardId,
		@RequestHeader("Authorization") String token) {
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);

			pinBoardService.deletePinBoard(pinId, boardId, userId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
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