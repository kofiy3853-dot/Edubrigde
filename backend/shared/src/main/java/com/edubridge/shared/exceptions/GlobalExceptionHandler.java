package com.edubridge.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edubridge.shared.dto.ApiError;
import com.edubridge.shared.dto.ApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(ResourceNotFoundException ex) {
        ApiError error = new ApiError("NOT_FOUND", ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(error));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException ex) {
        ApiError error = new ApiError(ex.getCode(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(error));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Void>> handleConflict(ConflictException ex) {
        ApiError error = new ApiError(ex.getCode(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, List<String>> details = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            details.computeIfAbsent(fieldError.getField(), k -> List.of())
                .add(fieldError.getDefaultMessage());
        });
        ApiError error = new ApiError("VALIDATION_FAILED", "Validation failed", details);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ApiResponse.error(error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex) {
        ApiError error = new ApiError("INTERNAL_ERROR", "An unexpected error occurred", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error(error));
    }
}
