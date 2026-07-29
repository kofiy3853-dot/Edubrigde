# ADR-016: Event-Driven Notification Architecture

## Status

**LOCKED** - Permanent architectural decision

## Context

EduBridge OS requires a centralized notification system that handles email verification, account notifications, and security alerts. Multiple domains need to send notifications but should not implement their own delivery mechanisms.

## Decision

**Event-Driven Notification Architecture.** Notifications must never contain business logic. Domains will emit events, and the Notification Module will determine how, when, and where notifications are delivered. No future domain is permitted to implement its own notification delivery mechanisms.

### Architecture

```
Future Domains
    |
Events
    |
Kafka
    |
Notification Layer
    |
Preference Validation
    |
Policy Validation
    |
Delivery Validation
    |
Notification
    |
Monitoring
    |
Audit Layer
    |
User
```

### Notification Types

| Type | Trigger | Channel |
|------|---------|---------|
| EMAIL_VERIFICATION | Registration | EMAIL |
| ACCOUNT_CREATED | User created | EMAIL |
| ACCOUNT_ACTIVATED | Email verified | EMAIL |
| PASSWORD_CHANGED | Password reset | EMAIL |
| SESSION_REVOKED | Session revoked | EMAIL |
| ACCOUNT_LOCKED | Too many failures | EMAIL |
| ACCOUNT_SUSPENDED | Admin action | EMAIL |
| PROFILE_UPDATED | Profile changed | EMAIL |
| SECURITY_ALERT | Security event | EMAIL |

### Notification Flow

1. Domain emits Kafka event
2. Notification Module receives event
3. User preferences validated
4. Notification policy checked
5. Delivery validated
6. Notification created (PENDING)
7. Notification sent (SENT)
8. Delivery confirmed (DELIVERED)
9. Audit logged

### Delivery States

```
PENDING -> SENT -> DELIVERED
    |       |
    |       +-> FAILED -> RETRYING -> SENT
    |
    +-> EXPIRED
```

## Consequences

### Benefits

1. Centralized notification management
2. Consistent user experience
3. Preference-aware delivery
4. Auditable notification history
5. Easy to add new channels
6. Clear domain boundaries

### Trade-offs

1. Single point of notification delivery
2. All domains depend on Notification Module
3. More complex event handling
4. Higher initial complexity

## Scope

**Permanent** - This decision applies to all notifications in EduBridge OS.

## Related Decisions

- ADR-012: Identity Domain as Guardian Domain
- ADR-015: User-Centric Domain Architecture
- ADR-008: Kafka Event-Driven Architecture
