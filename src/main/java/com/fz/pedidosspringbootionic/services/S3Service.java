package com.fz.pedidosspringbootionic.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucket;

    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            s3client.putObject(new PutObjectRequest(bucket, "teste.jpg", file));
        } catch (AmazonClientException e) {
            e.printStackTrace();
        }
    }
}
