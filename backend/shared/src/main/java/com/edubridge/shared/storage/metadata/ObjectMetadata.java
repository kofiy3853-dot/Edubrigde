package com.edubridge.shared.storage.metadata;

import lombok.Data;
import lombok.Builder;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class ObjectMetadata {

    private String id;
    private String objectName;
    private String bucket;
    private String originalFilename;
    private String contentType;
    private long fileSize;
    private int version;
    private String ownerUserId;
    private String ownerEntityType;
    private Instant uploadedAt;
    private Instant expiresAt;
    private String checksum;
    private Map<String, String> customMetadata;
    private StorageCategory category;
    private AccessLevel accessLevel;
    private boolean deleted;
    private Instant deletedAt;

    public enum StorageCategory {
        STUDENT_DOCUMENT,
        STUDENT_CERTIFICATE,
        LEARNING_RESOURCE,
        APPLICATION_DOCUMENT,
        REPORT,
        TEMPORARY_UPLOAD,
        MEDIA_ASSET,
        GENERATED_FILE
    }

    public enum AccessLevel {
        PRIVATE,
        RESTRICTED,
        MANAGED,
        POLICY_CONTROLLED
    }
}
