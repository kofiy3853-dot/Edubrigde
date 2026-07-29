package com.edubridge.identity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePreferencesRequest {

    private Boolean emailNotifications;
    private Boolean smsNotifications;
    private Boolean pushNotifications;
    private Boolean marketingEmails;
    private Boolean sessionReminders;
    private Boolean paymentNotifications;
    private Boolean securityAlerts;
    private Boolean academicUpdates;
    private Boolean darkMode;
    private Boolean compactMode;
    private Boolean sidebarCollapsed;
    private Boolean twoFactorEnabled;

    @NotNull(message = "Session timeout is required")
    private Integer sessionTimeoutMinutes;

    private Boolean loginNotifications;
}
