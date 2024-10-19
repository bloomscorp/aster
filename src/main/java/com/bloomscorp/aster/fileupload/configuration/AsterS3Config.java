package com.bloomscorp.aster.fileupload.configuration;

import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class AsterS3Config {

    private final String s3Region;
    private final String s3AccessKey;
    private final String s3SecretKey;


    public AsterS3Config(
        String s3Region,
        String s3AccessKey,
        String s3SecretKey
    ) {
        this.s3Region = s3Region;
        this.s3AccessKey = s3AccessKey;
        this.s3SecretKey = s3SecretKey;
    }


    @Bean
    public S3Client s3Client() {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
            s3AccessKey,
            s3SecretKey
        );

        return S3Client
            .builder()
            .region(Region.of(s3Region))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .build();
    }
}
