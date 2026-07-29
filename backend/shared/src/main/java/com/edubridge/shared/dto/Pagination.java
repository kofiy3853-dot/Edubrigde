package com.edubridge.shared.dto;

public record Pagination(
    int page,
    int limit,
    long total,
    int totalPages
) {}
