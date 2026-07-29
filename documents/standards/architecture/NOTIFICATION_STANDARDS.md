# Notification Standards

## LOCKED STANDARDS

These standards are permanently locked and cannot be modified.

---

## 1. Event-Driven Notification Architecture

Notifications must never contain business logic. Domains emit events, Notification Module handles delivery. No domain may implement its own notification delivery.

---

## 2. Notification Types

| Type | Trigger | Purpose |
|------|---------|---------|
| EMAIL_VERIFICATION | Registration | Verify email |
| ACCOUNT_CREATED | User created | Welcome |
| ACCOUNT_ACTIVATED | Email verified | Confirmation |
| PASSWORD_CHANGED | Password reset | Security alert |
| SESSION_REVOKED | Session revoked | Security alert |
| ACCOUNT_LOCKED | Failed logins | Security alert |
| ACCOUNT_SUSPENDED | Admin action | Account status |
| PROFILE_UPDATED | Profile changed | Confirmation |
| SECURITY_ALERT | Security event | Security alert |

---

## 3. Notification Channels

| Channel | Status | Purpose |
|---------|--------|---------|
| EMAIL | Active | Primary delivery |
| SMS | Reserved | Future |
| PUSH | Reserved | Future |
| IN_APP | Reserved | Future |

---

## 4. Delivery States

```
PENDING -> SENT -> DELIVERED
    |       |
    |       +-> FAILED -> RETRYING -> SENT
    |
    +-> EXPIRED
```

---

## 5. Email Verification Standards

### Workflow

1. User registers
2. Verification email queued
3. Token generated (24h expiry)
4. Email sent
5. User clicks link
6. Token validated
7. Account activated
8. Audit logged

### Rules

- Unverified accounts cannot access protected resources
- Verification tokens are single-use
- Tokens expire after 24 hours

---

## 6. Account Notification Standards

### Supported Events

- Account created
- Account activated
- Profile updated
- Password changed
- Session revoked
- Account locked
- Account suspended

### Rules

- No silent account modifications
- All changes are notified
- Notifications are auditable

---

## 7. Security Alert Standards

### Required Support

- Unauthorized login attempts
- Password changes
- Session revocations
- Account lock events
- Security preference changes

### Rules

- Security events are always observable
- Security alerts cannot be disabled
- All security events are logged

---

## 8. Notification Preference Standards

### Supported Preferences

- emailNotifications
- securityAlerts
- sessionReminders
- paymentNotifications
- academicUpdates

### Rules

- Preferences are independently configurable
- Security alerts cannot be disabled
- Preference changes are logged

---

## 9. Notification API Standards

### Endpoints

```
GET  /api/v1/identity/notifications
GET  /api/v1/identity/notifications/unread
GET  /api/v1/identity/notifications/stats
PUT  /api/v1/identity/notifications/{id}/read
```

---

## 10. Security Standards

### Required

- Preference validation
- Policy validation
- Delivery validation
- Audit logging
- Security monitoring
- Access validation
- Notification monitoring

### Prohibited

- Direct domain email delivery
- Hardcoded notification policies
- Notifications without auditing
- Preference bypass
- Security alerts without monitoring
- Unauthorized notification access
- Cross-domain notification ownership

---

## 11. Notification Directory Structure

```
identity/
  entities/
    Notification.java
  repositories/
    NotificationRepository.java
  services/
    NotificationService.java
  controllers/
    NotificationController.java
  dto/
    NotificationResponse.java
    NotificationStatsResponse.java
```
