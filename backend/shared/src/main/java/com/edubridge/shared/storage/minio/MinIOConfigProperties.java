package com.edubridge.shared.storage.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinIOConfigProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String region;
    private Buckets buckets = new Buckets();
    private Defaults defaults = new Defaults();

    @Data
    public static class Buckets {
        private String studentDocuments;
        private String studentCertificates;
        private String learningResources;
        private String applicationDocuments;
        private String reports;
        private String temporaryUploads;
        private String mediaAssets;
        private String generatedFiles;

        public Map<String, String> asMap() {
            return Map.of(
                "student-documents", studentDocuments,
                "student-certificates", studentCertificates,
                "learning-resources", learningResources,
                "application-documents", applicationDocuments,
                "reports", reports,
                "temporary-uploads", temporaryUploads,
                "media-assets", mediaAssets,
                "generated-files", generatedFiles
            );
        }
    }

    @Data
    public static class Defaults {
        private Duration expiryDuration = Duration.ofMinutes(15);
        private String maxFileSize = "50MB";
        private List<String> allowedTypes = List.of();
        private boolean versioningEnabled = true;
        private boolean encryptionEnabled = true;
    }
}
