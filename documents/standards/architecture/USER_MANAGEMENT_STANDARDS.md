# User Management Standards

## LOCKED STANDARDS

These standards are permanently locked and cannot be modified.

---

## 1. User-Centric Domain Architecture

A user exists only once across the platform. Every domain extends user capabilities through relationships, not duplicates. The Identity Domain owns the complete user lifecycle.

---

## 2. User Profile Standards

### Profile Structure

| Category | Fields |
|----------|--------|
| Basic Info | firstName, lastName, email, phone, avatarUrl |
| Profile Info | country, city, address, postalCode, bio |
| Online | website, linkedinUrl |
| Preferences | timezone, language, currency |

### Rules

- One profile per user
- Profiles are extensible
- Business-specific profiles belong to future domains
- Profile updates require validation

---

## 3. Account Management Standards

### Responsibilities

- Account creation
- Profile management
- Preference management
- Account updates
- Account recovery
- Account status management
- Account monitoring

### Rules

- No duplicated profiles
- No cross-domain profiles
- No multiple user records

---

## 4. User Preference Standards

### Preference Categories

| Category | Settings |
|----------|----------|
| Notifications | email, sms, push, marketing, session, payment, security, academic |
| UI | darkMode, compactMode, sidebarCollapsed |
| Security | twoFactorEnabled, sessionTimeoutMinutes, loginNotifications |

### Rules

- Preferences are independently configurable
- Default values provided
- Changes are validated
- Changes are logged

---

## 5. User Lifecycle Standards

### Lifecycle States

```
PENDING -> VERIFIED -> ACTIVE -> LOCKED/SUSPENDED -> DEACTIVATED -> ARCHIVED
```

### State Transitions

| From | To | Trigger |
|------|----|---------|
| PENDING | VERIFIED | Email verification |
| VERIFIED | ACTIVE | First login |
| ACTIVE | LOCKED | Failed login attempts |
| ACTIVE | SUSPENDED | Admin action |
| LOCKED | ACTIVE | Admin unlock |
| SUSPENDED | ACTIVE | Admin action |
| ACTIVE | DEACTIVATED | User/Admin action |
| DEACTIVATED | ARCHIVED | Policy |

### Rules

- Every account has a valid state
- State transitions are logged
- Lifecycle bypass is prohibited

---

## 6. Profile Management Standards

### Required Support

- Profile updates
- Preference updates
- Avatar management
- Accessibility settings
- Notification settings
- Security preferences

### Rules

- Business-specific profiles belong to future domains
- Profile updates require validation
- Profile changes are auditable

---

## 7. User API Standards

### Endpoints

```
GET  /api/v1/identity/users/profile
PUT  /api/v1/identity/users/profile
GET  /api/v1/identity/users/preferences
PUT  /api/v1/identity/users/preferences
PUT  /api/v1/identity/users/status
```

---

## 8. Security Standards

### Required

- Profile validation
- Session validation
- Permission validation
- Audit logging
- Security monitoring
- Account policies
- Lifecycle validation

### Prohibited

- Duplicated user profiles
- Cross-domain user ownership
- Lifecycle bypass
- Unauthorized profile modifications
- Hardcoded account policies
- Profile updates without validation
- Business-specific user implementations

---

## 9. User Events Standards

### Future Kafka Events

- UserCreatedEvent
- ProfileUpdatedEvent
- PreferenceUpdatedEvent
- AccountActivatedEvent
- AccountLockedEvent
- AccountSuspendedEvent
- AccountArchivedEvent
- UserDeletedEvent

---

## 10. User Directory Structure

```
identity/
  entities/
    UserProfile.java
    UserPreferences.java
  repositories/
    UserProfileRepository.java
    UserPreferencesRepository.java
  services/
    UserManagementService.java
  controllers/
    UserController.java
  dto/
    UpdateProfileRequest.java
    UserProfileResponse.java
    UpdatePreferencesRequest.java
    UserPreferencesResponse.java
```
