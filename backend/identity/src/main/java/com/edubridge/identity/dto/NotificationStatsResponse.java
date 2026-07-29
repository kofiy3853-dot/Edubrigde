package com.edubridge.identity.dto;

import lombok.Data;

@Data
public class NotificationStatsResponse {

    private long totalNotifications;
    private long unreadCount;
    private long sentCount;
    private long deliveredCount;
    private long failedCount;
}
