package com.ssafy.xmagazine.domain.board;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@Tag(name = "Board", description = "Board API")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;

	@GetMapping("/user/{userId}")
	@Operation(summary = "사용자 ID로 Board 조회", description = "사용자 ID로 Board를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<BoardDto>> selectBoardByUserId(@PathVariable int userId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int limit) {
		int offset = (page - 1) * limit;
		return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoardByUserId(userId, offset, limit));
	}

	@GetMapping("/{boardId}")
	@Operation(summary = "ID로 Board 조회", description = "ID로 Board를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<BoardDto> selectBoardById(@PathVariable int boardId) {
		return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoardById(boardId));
	}

	@PostMapping
	@Operation(summary = "Board 등록", description = "새로운 Board를 등록합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> insertBoard(@RequestBody BoardDto boardDto) {
		boardService.insertBoard(boardDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{boardId}")
	@Operation(summary = "Board 수정", description = "ID로 Board를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> updateBoard(@PathVariable int boardId, @RequestBody BoardDto boardDto) {
		boardDto.setBoardId(boardId);
		boardService.updateBoard(boardDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/thumbnail/{boardId}")
	@Operation(summary = "Board 수정", description = "ID로 Board를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> updateThumbnail(@PathVariable int boardId, @RequestBody BoardDto boardDto) {
		boardDto.setBoardId(boardId);
		boardService.updateThumbnail(boardDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{boardId}")
	@Operation(summary = "Board 삭제", description = "ID로 Board를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> deleteBoard(@PathVariable int boardId) {
		boardService.deleteBoard(boardId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}