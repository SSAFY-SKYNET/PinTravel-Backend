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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.xmagazine.exception.UnAuthorizedException;
import com.ssafy.xmagazine.util.JWTUtil;

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
	private final JWTUtil jwtUtil;

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

	@GetMapping("/list")
	@Operation(summary = "사용자 ID로 Board List 조회", description = "사용자 ID로 Board List를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<BoardDto>> selectBoardListByUserId(
		@RequestHeader("Authorization") String token) {
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);

			return ResponseEntity.status(HttpStatus.CREATED).body(boardService.selectBoardListByUserId(userId));
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
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
	public ResponseEntity<Integer> insertBoard(@RequestBody BoardDto boardDto,
		@RequestHeader("Authorization") String token) {
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);
			boardDto.setUserId(userId);

			int boardId = boardService.insertBoard(boardDto);

			return ResponseEntity.status(HttpStatus.CREATED).body(boardId);
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{boardId}")
	@Operation(summary = "Board 수정", description = "ID로 Board를 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "403", description = "FORBIDDEN")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<BoardDto> updateBoard(@PathVariable int boardId, @RequestBody BoardDto boardDto,
		@RequestHeader("Authorization") String token) {
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);

			// 기존 Board 조회
			BoardDto existingBoard = boardService.selectBoardById(boardId);
			if (existingBoard == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			// 수정 권한 확인
			if (existingBoard.getUserId() != userId) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			System.out.println(boardId);
			// Board 수정
			boardDto.setBoardId(boardId);
			boardService.updateBoard(boardDto);

			// 수정된 Board 조회
			BoardDto updatedBoard = boardService.selectBoardById(boardId);
			return ResponseEntity.ok(updatedBoard);
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/thumbnail/{boardId}")
	@Operation(summary = "Board 수정", description = "ID로 Board Thumbnail를 수정합니다.")
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