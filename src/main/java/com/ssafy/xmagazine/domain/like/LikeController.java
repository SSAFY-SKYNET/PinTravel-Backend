package com.ssafy.xmagazine.domain.like;

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
@RequestMapping("/likes")
@Tag(name = "Like", description = "Like API")
public class LikeController {
	private final LikeService likeService;
	private final JWTUtil jwtUtil;

	public LikeController(LikeService likeService, JWTUtil jwtUtil) {
		this.likeService = likeService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/{pinId}")
	@Operation(summary = "좋아요 추가", description = "새로운 좋아요를 추가합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Void> insertLike(@PathVariable int pinId, @RequestHeader("Authorization") String token) {
		System.out.println("insertLike 진입 성공");
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);
			likeService.insertLike(pinId, userId);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/{pinId}")
	@Operation(summary = "좋아요 삭제", description = "좋아요를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> deleteLike(@PathVariable int pinId,
		@RequestHeader("Authorization") String token) {
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);

			System.out.println("userId = " + userId);
			System.out.println("pinId = " + pinId);
			likeService.deleteLike(pinId, userId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/pin/{pinId}")
	@Operation(summary = "핀 좋아요 목록 조회", description = "특정 핀의 좋아요 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<LikeDto>> selectLikesByPin(@PathVariable int pinId) {
		List<LikeDto> likes = likeService.selectLikesByPin(pinId);
		return ResponseEntity.ok(likes);
	}

	@GetMapping("/user/{userId}")
	@Operation(summary = "사용자 좋아요 목록 조회", description = "특정 사용자의 좋아요 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<LikeDto>> selectLikesByUser(@PathVariable int userId) {
		List<LikeDto> likes = likeService.selectLikesByUser(userId);
		return ResponseEntity.ok(likes);
	}
}