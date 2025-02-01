package com.caaguirre.develop.service;

public interface S3Service {

    void putS3Object(String bucketName, String objectPath);

}
