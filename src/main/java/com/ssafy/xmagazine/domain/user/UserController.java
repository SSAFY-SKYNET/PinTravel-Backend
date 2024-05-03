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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/")
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

    @PostMapping("/login")
    @Operation(summary = "유저 로그인", description = "유저 로그인을 처리합니다.")
    public void login(@RequestBody UserDto user) {
        log.info("UserDto: {}", user);
        userService.login(user);
    }

    @PostMapping("/logout")
    @Operation(summary = "유저 로그아웃", description = "유저 로그아웃을 처리합니다.")
    public void logout(@RequestBody UserDto user) {
        userService.logout(user);
    }

    @PostMapping("/update")
    @Operation(summary = "유저 정보 업데이트", description = "유저 정보를 업데이트합니다.")
    public void updateUser(@RequestBody UserDto user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "유저 삭제", description = "ID로 유저를 삭제합니다.")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
