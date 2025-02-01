package com.caaguirre.develop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class S3ServiceImpl implements S3Service {

    private final Region region;

    private final S3Client s3Client;

    S3ServiceImpl(){
        region = Region.US_EAST_1;
        s3Client = S3Client.builder()
                .region(region)
                .build();
    }

    @Override
    public void putS3Object(String bucketName, String objectPath) {
        try {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("author", "Mary Doe");
            metadata.put("version", "1.0.0.0");
            File file = new File(objectPath);
            String objectKey = file.getName();

            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .metadata(metadata)
                    .build();

            s3Client.putObject(putOb, RequestBody.fromFile(file));
            log.info("Successfully placed " + objectKey + " into bucket " + bucketName);

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

}
