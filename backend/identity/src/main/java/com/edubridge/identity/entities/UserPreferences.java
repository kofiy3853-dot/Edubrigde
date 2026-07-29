package com.edubridge.identity.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_preferences", schema = "identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreferences extends AuditableEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "email_notifications", nullable = false)
    @Builder.Default
    private Boolean emailNotifications = true;

    @Column(name = "sms_notifications", nullable = false)
    @Builder.Default
    private Boolean smsNotifications = false;

    @Column(name = "push_notifications", nullable = false)
    @Builder.Default
    private Boolean pushNotifications = true;

    @Column(name = "marketing_emails", nullable = false)
    @Builder.Default
    private Boolean marketingEmails = false;

    @Column(name = "session_reminders", nullable = false)
    @Builder.Default
    private Boolean sessionReminders = true;

    @Column(name = "payment_notifications", nullable = false)
    @Builder.Default
    private Boolean paymentNotifications = true;

    @Column(name = "security_alerts", nullable = false)
    @Builder.Default
    private Boolean securityAlerts = true;

    @Column(name = "academic_updates", nullable = false)
    @Builder.Default
    private Boolean academicUpdates = true;

    @Column(name = "dark_mode", nullable = false)
    @Builder.Default
    private Boolean darkMode = false;

    @Column(name = "compact_mode", nullable = false)
    @Builder.Default
    private Boolean compactMode = false;

    @Column(name = "sidebar_collapsed", nullable = false)
    @Builder.Default
    private Boolean sidebarCollapsed = false;

    @Column(name = "two_factor_enabled", nullable = false)
    @Builder.Default
    private Boolean twoFactorEnabled = false;

    @Column(name = "session_timeout_minutes", nullable = false)
    @Builder.Default
    private Integer sessionTimeoutMinutes = 30;

    @Column(name = "login_notifications", nullable = false)
    @Builder.Default
    private Boolean loginNotifications = true;
}
