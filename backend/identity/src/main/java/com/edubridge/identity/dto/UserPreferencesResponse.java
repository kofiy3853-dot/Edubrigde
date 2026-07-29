package com.edubridge.identity.dto;

import lombok.Data;

@Data
public class UserPreferencesResponse {

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
    private Integer sessionTimeoutMinutes;
    private Boolean loginNotifications;
}
