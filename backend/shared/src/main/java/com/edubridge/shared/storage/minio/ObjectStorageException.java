package com.edubridge.shared.storage.minio;

import lombok.Getter;

@Getter
public class ObjectStorageException extends RuntimeException {

    private final String bucket;
    private final String objectName;

    public ObjectStorageException(String message) {
        super(message);
        this.bucket = null;
        this.objectName = null;
    }

    public ObjectStorageException(String message, Throwable cause) {
        super(message, cause);
        this.bucket = null;
        this.objectName = null;
    }

    public ObjectStorageException(String message, String bucket, String objectName) {
        super(message);
        this.bucket = bucket;
        this.objectName = objectName;
    }
}
