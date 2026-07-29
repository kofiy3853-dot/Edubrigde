package com.edubridge.shared.storage.minio;

import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ObjectStorageService {

    private final MinioClient minioClient;
    private final MinIOConfigProperties configProperties;

    public StoredObject upload(String bucket, String objectName, InputStream inputStream, String contentType, Map<String, String> metadata) {
        try {
            ObjectWriteResponse response = minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .stream(inputStream, -1, 10485760)
                    .contentType(contentType)
                    .userMetadata(metadata != null ? metadata : Map.of())
                    .build()
            );

            log.info("Object uploaded: bucket={}, object={}, etag={}", bucket, objectName, response.etag());
            return new StoredObject(bucket, objectName, response.etag());
        } catch (Exception e) {
            log.error("Failed to upload object: bucket={}, object={}", bucket, objectName, e);
            throw new ObjectStorageException("Upload failed", e);
        }
    }

    public StoredObject uploadMultipart(String bucket, String path, MultipartFile file, Map<String, String> metadata) {
        String objectName = generateObjectName(path, file.getOriginalFilename());
        try {
            return upload(bucket, objectName, file.getInputStream(), file.getContentType(), metadata);
        } catch (Exception e) {
            throw new ObjectStorageException("Multipart upload failed", e);
        }
    }

    public InputStream download(String bucket, String objectName) {
        try {
            return minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build()
            );
        } catch (Exception e) {
            log.error("Failed to download object: bucket={}, object={}", bucket, objectName, e);
            throw new ObjectStorageException("Download failed", e);
        }
    }

    public String getPresignedUrl(String bucket, String objectName, Duration expiry) {
        try {
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucket)
                    .object(objectName)
                    .expiry((int) expiry.getSeconds())
                    .build()
            );
        } catch (Exception e) {
            log.error("Failed to generate presigned URL: bucket={}, object={}", bucket, objectName, e);
            throw new ObjectStorageException("Presigned URL generation failed", e);
        }
    }

    public void delete(String bucket, String objectName) {
        try {
            minioClient.removeObject(
                RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .build()
            );
            log.info("Object deleted: bucket={}, object={}", bucket, objectName);
        } catch (Exception e) {
            log.error("Failed to delete object: bucket={}, object={}", bucket, objectName, e);
            throw new ObjectStorageException("Delete failed", e);
        }
    }

    public void ensureBucketExists(String bucketName) {
        try {
            boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!exists) {
                minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(bucketName).build()
                );
                log.info("Bucket created: {}", bucketName);
            }
        } catch (Exception e) {
            log.error("Failed to ensure bucket exists: {}", bucketName, e);
            throw new ObjectStorageException("Bucket check failed", e);
        }
    }

    private String generateObjectName(String path, String originalFilename) {
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return path + "/" + UUID.randomUUID() + extension;
    }
}
