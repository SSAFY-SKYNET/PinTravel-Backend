package com.ssafy.xmagazine.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.xmagazine.util.JWTUtil;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;
	private final JWTUtil jwtUtil;

	@PostMapping("/signup")
	@Operation(summary = "유저 생성", description = "새로운 유저를 생성합니다.")
	public void insertUser(@RequestBody UserDto user) {
		log.info("UserDto: {}", user);
		userService.insertUser(user);
	}

	@GetMapping("/")
	@Operation(summary = "모든 유저 조회", description = "모든 유저를 조회합니다.")
	public ResponseEntity<List<UserDto>> selectAllUser() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.selectAllUser());
	}

	@GetMapping("/{id}")
	@Operation(summary = "ID로 유저 조회", description = "ID로 유저를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<UserDto> selectUserById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.selectUserById(id));
	}

	// @PostMapping("/login")
	// @Operation(summary = "유저 로그인", description = "유저 로그인을 처리합니다.")
	// public void login(@RequestBody UserDto user) {
	//     log.info("UserDto: {}", user);
	//     userService.login(user);
	// }

	@PostMapping("/logout")
	@Operation(summary = "유저 로그아웃", description = "유저 로그아웃을 처리합니다.")
	public void logout(@RequestBody UserDto user) {
		userService.logout(user);
	}

	@PostMapping("/update")
	@Operation(summary = "유저 정보 업데이트", description = "유저 정보를 업데이트합니다.")
	public void updateUser(@RequestBody UserDto user, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("Authorization");
		if (jwtUtil.checkToken(token)) {
			log.info("사용 가능한 토큰!!!");
			try {
				int userId = jwtUtil.getUserId(token);
				user.setUserId(userId);
				userService.updateUser(user);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패: {}", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "유저 삭제", description = "ID로 유저를 삭제합니다.")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	@PostMapping("/oauthLogin")
	@Operation(summary = "OAuth 로그인", description = "OAuth 로그인을 처리합니다.")
	public ResponseEntity<Map<String, Object>> oauthLogin(@RequestBody UserDto user) {
		
		log.info("OAuth 로그인 성공");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDto loginUser = userService.oauthLogin(user);
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("Access Token: {}", accessToken);
				log.debug("Refresh Token: {}", refreshToken);

				userService.saveRefreshToken(loginUser.getUserId(), refreshToken);

				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.debug("로그인 에러 발생: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@PostMapping("/login")
	@Operation(summary = "유저 로그인", description = "유저 로그인을 처리합니다.")
	public ResponseEntity<Map<String, Object>> login(
		@RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		log.debug("Login User: {}", userDto);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("Access Token: {}", accessToken);
				log.debug("Refresh Token: {}", refreshToken);

				userService.saveRefreshToken(loginUser.getUserId(), refreshToken);

				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.debug("로그인 에러 발생: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@GetMapping("/info")
	@Operation(summary = "ID로 유저 조회", description = "ID로 유저를 조회합니다.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "404", description = "NOT FOUND")
	@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
	public ResponseEntity<Map<String, Object>> findById(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("Authorization");
		if (jwtUtil.checkToken(token)) {
			log.info("사용 가능한 토큰!!!");
			try {
				int userId = jwtUtil.getUserId(token);
				UserDto userDto = userService.selectUserById(userId);
				resultMap.put("userInfo", userDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패: {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@PostMapping("/refresh")
	@Operation(summary = "토큰 재발급", description = "Refresh Token을 이용하여 Access Token을 재발급합니다.")
	public ResponseEntity<Map<String, Object>> tokenRegeneration(@RequestBody UserDto userDto,
		HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("Token: {}, UserDto: {}", token, userDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(userDto.getUserId());
				log.debug("Access Token: {}", accessToken);
				log.debug("정상적으로 Access Token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("Refresh Token도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@GetMapping("/logout/{userId}")
	@Operation(summary = "유저 로그아웃", description = "유저 로그아웃을 처리합니다.")
	public ResponseEntity<Map<String, Object>> logout(
		@PathVariable("userId") @Parameter(description = "로그아웃 할 회원의 아이디.", required = true) int userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleteRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}
}
