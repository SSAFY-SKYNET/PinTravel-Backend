package com.ssafy.xmagazine.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.ssafy.xmagazine.Service.ImageUploadService;

@Controller
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    public ImageUploadController(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile imageFile, Model model)
            throws AmazonServiceException, SdkClientException, IOException {
        // 이미지 업로드 로직
        String imageUrl = imageUploadService.uploadImage(imageFile);

        // view의 이름을 반환하여 view를 렌더링 하도록함
        return ResponseEntity.ok(imageUrl);
    }

}
