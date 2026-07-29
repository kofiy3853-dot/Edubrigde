package com.edubridge.shared.storage.minio;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinIOConfig {

    private final MinIOConfigProperties properties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(properties.getEndpoint())
            .credentials(properties.getAccessKey(), properties.getSecretKey())
            .region(properties.getRegion())
            .build();
    }
}
