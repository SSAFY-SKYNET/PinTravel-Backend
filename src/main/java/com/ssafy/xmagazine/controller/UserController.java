package com.ssafy.xmagazine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.xmagazine.dto.UserDto;
import com.ssafy.xmagazine.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public void insertUser(@RequestBody UserDto user) {
        userService.insertUser(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 유저 조회", description = "ID로 유저를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "NOT FOUND")
    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    public ResponseEntity<UserDto> selectUserById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.selectUserById(id));
    }
}
