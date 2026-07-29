package com.edubridge.identity.dto;

import lombok.Data;

@Data
public class AuditStatsResponse {

    private long totalLogs;
    private long successCount;
    private long failureCount;
    private long deniedCount;
    private long errorCount;
}
