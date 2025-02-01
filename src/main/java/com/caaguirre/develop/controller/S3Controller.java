package com.caaguirre.develop.controller;

import com.caaguirre.develop.service.S3Service;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/files")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping(path = "/upload" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file,
                                  @RequestParam("bucket") String bucketName) throws IOException {
        // Get system temp directory
        String tempDir = System.getProperty("java.io.tmpdir");

        // Create file with original name in temp directory
        File savedFile = new File(tempDir, Objects.requireNonNull(file.getOriginalFilename()));
        file.transferTo(savedFile);

        // Get absolute path
        String absolutePath = savedFile.getAbsolutePath();

        s3Service.putS3Object(bucketName, absolutePath);
        return ResponseEntity.ok("");
    }

}
