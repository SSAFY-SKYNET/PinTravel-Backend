package com.ssafy.xmagazine.domain.comment;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@Tag(name = "Comment", description = "Comment API")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping("/pin/{pinId}")
	@Operation(summary = "잡지 ID로 댓글 조회", description = "잡지 ID로 댓글을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<CommentDto>> selectCommentByPinId(@PathVariable int pinId) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.selectCommentByPinId(pinId));
	}

	@GetMapping("/user/{userId}")
	@Operation(summary = "사용자 ID로 댓글 조회", description = "사용자 ID로 댓글을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<CommentDto>> selectCommentByUserId(@PathVariable int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.selectCommentByUserId(userId));
	}

	@GetMapping("/{commentId}")
	@Operation(summary = "commentId로 댓글 조회", description = "commentId로 댓글을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<CommentDto> selectCommentById(@PathVariable int commentId) {
		return ResponseEntity.status(HttpStatus.OK).body(commentService.selectCommentById(commentId));
	}

	@PostMapping
	@Operation(summary = "댓글 등록", description = "새로운 댓글을 등록합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<CommentDto>> insertComment(@RequestBody CommentDto commentDto) {
		commentService.insertComment(commentDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(commentService.selectCommentByPinId(commentDto.getPinId()));
	}

	@PutMapping("/{commentId}")
	@Operation(summary = "댓글 수정", description = "commentId로 댓글을 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<CommentDto>> updateComment(@PathVariable int commentId,
			@RequestBody CommentDto commentDto) {
		commentDto.setCommentId(commentId);
		commentService.updateComment(commentDto);
		return ResponseEntity.status(HttpStatus.OK).body(commentService.selectCommentByPinId(commentDto.getPinId()));
	}

	@DeleteMapping("/{commentId}")
	@Operation(summary = "댓글 삭제", description = "commentId로 댓글을 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> deleteComment(@PathVariable int id) {
		commentService.deleteComment(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}