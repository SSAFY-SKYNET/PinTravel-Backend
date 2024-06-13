package com.ssafy.xmagazine.Service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageUploadService {
    private final AmazonS3 amazonS3;
    private final String bucket;

    public ImageUploadService(AmazonS3 amazonS3, @Value("${s3.bucket}") String bucket) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
    }

    public String uploadImage(MultipartFile imageFile) throws AmazonServiceException, SdkClientException, IOException {
        // 고유한 파일 이름 생성
        String originalFileName = imageFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String fileName = UUID.randomUUID().toString() + extension;

        // 메타데이터 생성
        ObjectMetadata metadata = extractMetadata(imageFile);

        try {
            // 파일 업로드
            amazonS3.putObject(bucket, fileName, imageFile.getInputStream(), metadata);

            // 업로드된 파일의 S3 URL 반환
            String fileUrl = amazonS3.getUrl(bucket, fileName).toString();
            log.info("File uploaded successfully. URL: {}", fileUrl);
            return fileUrl;
        } catch (SdkClientException | IOException e) {
            log.error("File upload failed", e);
            throw e;
        }
    }

    private ObjectMetadata extractMetadata(MultipartFile imageFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imageFile.getContentType());
        metadata.setContentLength(imageFile.getSize());
        return metadata;
    }
}
