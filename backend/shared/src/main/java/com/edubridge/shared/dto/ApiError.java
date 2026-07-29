package com.edubridge.shared.dto;

public record ApiError(
    String code,
    String message,
    java.util.Map<String, java.util.List<String>> details
) {}
