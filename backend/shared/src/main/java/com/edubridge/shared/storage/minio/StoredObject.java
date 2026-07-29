package com.edubridge.shared.storage.minio;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class StoredObject {
    private String bucket;
    private String objectName;
    private String etag;
}
