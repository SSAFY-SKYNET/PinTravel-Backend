package com.ssafy.xmagazine.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.xmagazine.config.S3Config;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;

@Service
public class ImageUploadService {
    private final AmazonS3 amazonS3;
    private final S3Config s3Config;

    public ImageUploadService(AmazonS3 amazonS3, S3Config s3Config) {
        this.amazonS3 = amazonS3;
        this.s3Config = s3Config;
    }

    @Value("${s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile imageFile) throws AmazonServiceException, SdkClientException, IOException {
        // 업로드할 파일의 이름을 변경
        String originalFileName = imageFile.getOriginalFilename();
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);

        // S3에 업로드할 파일의 메타데이터 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imageFile.getContentType());
        metadata.setContentLength(imageFile.getSize());

        // S3에 파일 업로드
        amazonS3.putObject(bucket, fileName, imageFile.getInputStream(), metadata);

        // 업로드할 파일의 s3 URL 주소 변환
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    public String extractMetadataUsingLibrary(MultipartFile imageFile) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(imageFile.getInputStream());

        // 예를 들어, EXIF 정보 중에서 카메라 제조사를 가져오는 예제
        ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        if (directory != null && directory.containsTag(ExifIFD0Directory.TAG_MAKE)) {
            return directory.getString(ExifIFD0Directory.TAG_MAKE);
        }

        return "No EXIF information found";
    }
}
