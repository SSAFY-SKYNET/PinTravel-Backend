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
import com.amazonaws.services.s3.AmazonS3;
import com.ssafy.xmagazine.Service.ImageUploadService;
import com.ssafy.xmagazine.config.S3Config;

@Controller
public class ImageUploadController {
    private final AmazonS3 amazonS3;
    private final S3Config s3Config;
    private final ImageUploadService imageUploadService;

    public ImageUploadController(AmazonS3 amazonS3, S3Config s3Config, ImageUploadService imageUploadService) {
        this.amazonS3 = amazonS3;
        this.s3Config = s3Config;
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
