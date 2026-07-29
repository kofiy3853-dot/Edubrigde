# ADR-015: User-Centric Domain Architecture

## Status

**LOCKED** - Permanent architectural decision

## Context

EduBridge OS requires a unified approach to user management where user information exists only once across the entire platform. Multiple domains need user data but should not create duplicate user profiles or manage user lifecycle independently.

## Decision

**User-Centric Domain Architecture.** A user should exist only once throughout the entire platform. Every future domain must extend the user's capabilities through relationships rather than creating duplicate user information. The Identity Domain permanently owns the complete user lifecycle.

### Architecture

```
Identity Domain (Owner)
    |
User
    |
UserProfile
    |
UserPreferences
    |
Lifecycle Management
    |
Account Management
    |
+---+---+---+---+
|   |   |   |   |
Academic Financial Learning AI (all use User, don't own)
```

### User Lifecycle States

```
PENDING -> VERIFIED -> ACTIVE -> LOCKED/SUSPENDED -> DEACTIVATED -> ARCHIVED
```

### User Profile Structure

| Field | Purpose |
|-------|---------|
| Basic Info | firstName, lastName, email, phone |
| Profile Info | country, city, bio, website |
| Preferences | language, timezone, currency |
| Accessibility | darkMode, compactMode |
| Notifications | email, sms, push, security |
| Security | twoFactor, sessionTimeout |

### Domain Relationships

| Domain | Relationship |
|--------|--------------|
| Academic | References userId, stores academic data |
| Financial | References userId, stores payment data |
| Learning | References userId, stores learning data |
| AI | References userId, stores recommendations |

### Prohibited Actions

No other domain may:
- Create user profile tables
- Manage user lifecycle
- Own user preferences
- Duplicate user information

## Consequences

### Benefits

1. Single source of truth for users
2. Consistent user experience
3. Reduced data duplication
4. Easier privacy compliance
5. Simplified user management
6. Clear domain boundaries

### Trade-offs

1. All domains depend on Identity
2. Profile changes affect all domains
3. More complex cross-domain queries
4. Higher initial complexity

## Scope

**Permanent** - This decision applies to all domains in EduBridge OS.

## Related Decisions

- ADR-012: Identity Domain as Guardian Domain
- ADR-013: Zero-Trust Authentication Architecture
- ADR-014: Policy-Based RBAC Architecture
