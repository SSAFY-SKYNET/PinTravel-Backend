package com.ssafy.xmagazine.domain.pin;

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

@RestController
@RequestMapping("/pin")
@Tag(name = "Pin", description = "Pin API")
public class PinController {
	private final PinService pinService;
	private final JWTUtil jwtUtil;

	public PinController(PinService pinService, JWTUtil jwtUtil) {
		this.pinService = pinService;
		this.jwtUtil = jwtUtil;

	}

	@GetMapping("/{pinId}")
	@Operation(summary = "ID로 핀 조회", description = "ID로 핀을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<PinDto> getPinById(@PathVariable int pinId) {
		PinDto pin = pinService.selectPinById(pinId);
		if (pin == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pin);
	}

	@GetMapping("/likes")
	@Operation(summary = "좋아요 순으로 핀 목록 조회", description = "좋아요 순으로 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<PinDto>> getPinsByLikeCount() {
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByLikeCount());
	}

	@GetMapping("/likes/page")
	@Operation(summary = "좋아요 순으로 페이지 별 핀 목록 조회", description = "좋아요 순으로 페이지 별 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "성공적으로 핀 목록을 조회했습니다.")
	public ResponseEntity<List<PinDto>> getPinsByLikeCountAndPage(@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int limit) {
		int offset = (page - 1) * limit;
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByLikeCountAndPage(offset, limit));
	}

	@GetMapping("/user/{userId}")
	@Operation(summary = "유저 ID로 핀 목록 조회", description = "유저 ID로 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<PinDto>> getPinsByUserId(@PathVariable int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByUserId(userId));
	}

	@GetMapping("/user/{userId}/page")
	@Operation(summary = "유저 ID로 페이지 별 핀 목록 조회", description = "유저 ID로 페이지 별 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "성공적으로 핀 목록을 조회했습니다.")
	public ResponseEntity<List<PinDto>> getPinsByUserIdAndPage(@PathVariable int userId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int limit) {
		int offset = (page - 1) * limit;
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByUserIdAndPage(userId, offset, limit));
	}

	@GetMapping("/tag/{tagName}")
	@Operation(summary = "태그로 핀 목록 조회", description = "태그로 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<PinDto>> getPinsByTag(@PathVariable int tagId) {
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByTag(tagId));
	}

	@GetMapping("/tag/{tagId}/page")
	@Operation(summary = "태그로 페이지 별 핀 목록 조회", description = "태그로 페이지 별 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "성공적으로 핀 목록을 조회했습니다.")
	public ResponseEntity<List<PinDto>> getPinsByTagAndPage(@PathVariable int tagId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int limit) {
		int offset = (page - 1) * limit;
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByTagAndPage(tagId, offset, limit));
	}

	@GetMapping("/board/{boardId}")
	@Operation(summary = "게시판 ID로 핀 목록 조회", description = "게시판 ID로 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<PinDto>> getPinsByBoard(@PathVariable int boardId) {
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByBoard(boardId));
	}

	@GetMapping("/board/{boardId}/page")
	@Operation(summary = "게시판 ID로 페이지 별 핀 목록 조회", description = "게시판 ID로 페이지 별 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "성공적으로 핀 목록을 조회했습니다.")
	public ResponseEntity<List<PinDto>> getPinsByBoardAndPage(@PathVariable int boardId,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int limit) {
		int offset = (page - 1) * limit;
		return ResponseEntity.status(HttpStatus.OK).body(pinService.selectPinByBoardAndPage(boardId, offset, limit));
	}

	// @GetMapping("/{pinId}/nearby/page")
	// public ResponseEntity<List<PinDto>> getPinsByPinIdAndPage(@PathVariable int
	// pinId,
	// @RequestParam(defaultValue = "1") int page,
	// @RequestParam(defaultValue = "10") int limit) {
	// PinDto selectedPin = pinService.selectPinById(pinId);
	// if (selectedPin == null) {
	// return ResponseEntity.notFound().build();
	// }
	// double longitude = selectedPin.getLongitude();
	// double latitude = selectedPin.getLatitude();
	// int offset = (page - 1) * limit;
	// List<PinDto> nearbyPins = pinService.selectPinByPinIdAndPage(longitude,
	// latitude, offset, limit);
	// return ResponseEntity.ok(nearbyPins);
	// }

	@PostMapping
	@Operation(summary = "핀 등록", description = "새로운 핀을 등록합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> createPin(@RequestBody PinDto pin, @RequestHeader("Authorization") String token) {
		try {
			// JWT에서 userId 추출
			int userId = jwtUtil.getUserId(token);
			pin.setUserId(userId);

			pinService.insertPin(pin);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (UnAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{pinId}")
	@Operation(summary = "핀 수정", description = "ID로 핀을 수정합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "400", description = "BAD REQUEST")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> updatePin(@PathVariable int pinId, @RequestBody PinDto pin) {
		pin.setPinId(pinId);
		pinService.updatePin(pin);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{pinId}")
	@Operation(summary = "핀 삭제", description = "ID로 핀을 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO CONTENT")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Void> deletePin(@PathVariable int pinId) {
		pinService.deletePin(pinId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping("/multi-tag")
	@Operation(summary = "핀 목록 조회", description = "태그 이름으로 핀 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<List<PinDto>> getPinsByMultiTag(@RequestBody List<String> tagNames,
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int limit) {
		int offset = (page - 1) * limit;
		List<PinDto> pins = pinService.searchPinsByMultiTagAndPage(tagNames, offset, limit);
		return ResponseEntity.status(HttpStatus.OK).body(pins);
	}

	@GetMapping("/{pinId}/nearby/page")
	public ResponseEntity<List<PinDto>> getPinsByPinIdAndPage(@PathVariable int pinId,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int limit) {
		PinDto selectedPin = pinService.selectPinById(pinId);
		if (selectedPin == null) {
			return ResponseEntity.notFound().build();
		}
		double longitude = selectedPin.getLongitude();
		double latitude = selectedPin.getLatitude();
		int offset = (page - 1) * limit;
		List<PinDto> nearbyPins = pinService.selectPinByPinIdAndPage(longitude, latitude, offset, limit);
		return ResponseEntity.ok(nearbyPins);
	}
}
