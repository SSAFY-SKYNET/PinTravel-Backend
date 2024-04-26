package com.ssafy.xmagazine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.xmagazine.service.MagazineService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/magazine")
@Tag(name = "Magazine", description = "Magazine API")
@RequiredArgsConstructor
public class MagazineController {
	private final MagazineService magazineService;
}
