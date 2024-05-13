package com.ssafy.xmagazine.domain.like;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@Tag(name = "Like", description = "Like API")
public class LikeController {
	private final LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}

	@PostMapping
	@Operation(summary = "좋아요 추가", description = "새로운 좋아요를 추가합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Void> insertLike(@RequestBody LikeDto likeDto) {
		likeService.insertLike(likeDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{likeId}")
	@Operation(summary = "좋아요 삭제", description = "좋아요를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> deleteLike(@PathVariable int likeId) {
		likeService.deleteLike(likeId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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