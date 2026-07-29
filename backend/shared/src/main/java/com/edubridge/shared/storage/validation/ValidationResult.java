package com.edubridge.shared.storage.validation;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {

    private boolean valid;
    private List<String> errors;

    public static ValidationResult success() {
        return ValidationResult.builder()
            .valid(true)
            .errors(List.of())
            .build();
    }

    public static ValidationResult failure(List<String> errors) {
        return ValidationResult.builder()
            .valid(false)
            .errors(errors)
            .build();
    }
}
