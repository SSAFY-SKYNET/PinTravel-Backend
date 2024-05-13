package com.ssafy.xmagazine.domain.follower;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.ssafy.xmagazine.domain.user.UserDto;

@RestController
@RequestMapping("/followers")
@Tag(name = "Follower", description = "Follower API")
public class FollowerController {
	private final FollowerService followerService;

	public FollowerController(FollowerService followerService) {
		this.followerService = followerService;
	}

	@PostMapping
	@Operation(summary = "팔로우 추가", description = "새로운 팔로우를 추가합니다.")
	@ApiResponse(responseCode = "201", description = "CREATED")
	public ResponseEntity<Void> insertFollower(@RequestBody FollowerDto followerDto) {
		followerService.insertFollower(followerDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{followerId}/{followedId}")
	@Operation(summary = "팔로우 삭제", description = "팔로우를 삭제합니다.")
	@ApiResponse(responseCode = "204", description = "NO_CONTENT")
	public ResponseEntity<Void> deleteFollower(@PathVariable int followerId, @PathVariable int followedId) {
		followerService.deleteFollower(followerId, followedId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/followers/{followedId}")
	@Operation(summary = "팔로워 목록 조회", description = "특정 사용자의 팔로워 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<UserDto>> selectFollowers(@PathVariable int followedId) {
		List<UserDto> followers = followerService.selectFollowers(followedId);
		return ResponseEntity.ok(followers);
	}

	@GetMapping("/followings/{followerId}")
	@Operation(summary = "팔로잉 목록 조회", description = "특정 사용자의 팔로잉 목록을 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	public ResponseEntity<List<UserDto>> selectFollowings(@PathVariable int followerId) {
		List<UserDto> followings = followerService.selectFollowings(followerId);
		return ResponseEntity.ok(followings);
	}
}