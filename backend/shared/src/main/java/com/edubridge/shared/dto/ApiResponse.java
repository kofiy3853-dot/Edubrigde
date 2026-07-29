package com.edubridge.shared.dto;

import java.util.List;

public record ApiResponse<T>(
    boolean success,
    T data,
    ApiError error,
    Pagination pagination
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, null);
    }

    public static <T> ApiResponse<T> success(T data, Pagination pagination) {
        return new ApiResponse<>(true, data, null, pagination);
    }

    public static <T> ApiResponse<T> error(ApiError error) {
        return new ApiResponse<>(false, null, error, null);
    }
}
