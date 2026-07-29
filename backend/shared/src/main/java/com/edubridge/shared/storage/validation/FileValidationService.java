package com.edubridge.shared.storage.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileValidationService {

    private final com.edubridge.shared.storage.minio.MinIOConfigProperties configProperties;

    private static final long MAX_FILE_SIZE = 50 * 1024 * 1024;
    private static final Set<String> ALLOWED_TYPES = Set.of(
        "application/pdf",
        "image/png",
        "image/jpeg",
        "image/gif",
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        "application/vnd.ms-excel",
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    );

    public ValidationResult validate(MultipartFile file) {
        var errors = new java.util.ArrayList<String>();

        if (file == null || file.isEmpty()) {
            errors.add("File is empty");
            return ValidationResult.failure(errors);
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            errors.add("File size exceeds maximum allowed size of 50MB");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            errors.add("File type not allowed: " + contentType);
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            errors.add("Filename is required");
        }

        if (errors.isEmpty()) {
            log.debug("File validation passed: {}", originalFilename);
            return ValidationResult.success();
        }

        log.warn("File validation failed: {}", errors);
        return ValidationResult.failure(errors);
    }

    public List<String> getAllowedTypes() {
        return List.copyOf(ALLOWED_TYPES);
    }

    public long getMaxFileSize() {
        return MAX_FILE_SIZE;
    }
}
