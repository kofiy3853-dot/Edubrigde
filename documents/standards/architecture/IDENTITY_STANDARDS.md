# Identity Domain Standards

## LOCKED STANDARDS

These standards are permanently locked and cannot be modified.

---

## 1. Guardian Domain

The Identity Domain is the single source of truth for:
- Users
- Roles
- Permissions
- Authentication
- Authorization
- Sessions
- Security Policies

No other domain may implement its own user management.

---

## 2. User Standards

### User Categories

- Students
- Parents
- Consultants
- Mentors
- Administrators
- Super Administrators
- Support Staff

### User Fields

- id (UUID)
- email (unique)
- passwordHash (BCrypt)
- firstName, lastName
- phone
- avatarUrl
- emailVerified, phoneVerified
- accountLocked, accountLockedUntil
- failedLoginAttempts
- lastLoginAt
- passwordChangedAt
- status (ACTIVE, INACTIVE, SUSPENDED, PENDING_VERIFICATION)

---

## 3. Role Standards

### System Roles

| Role | Description |
|------|-------------|
| ROLE_STUDENT | Student access |
| ROLE_PARENT | Parent access |
| ROLE_CONSULTANT | Consultant access |
| ROLE_MENTOR | Mentor access |
| ROLE_SUPPORT | Support staff access |
| ROLE_ADMIN | Administrator access |
| ROLE_SUPER_ADMIN | Super administrator access |

### Role Rules

- Names prefixed with `ROLE_`
- System roles cannot be deleted
- Roles are globally unique

---

## 4. Permission Standards

### Permission Format

```
{ACTION}_{RESOURCE}
```

### Actions

- CREATE, READ, UPDATE, DELETE
- MANAGE, VIEW, EXPORT, IMPORT

### Resources

- USER, ROLE, PERMISSION, SESSION
- COURSE, ENROLLMENT
- PAYMENT, SCHOLARSHIP
- APPLICATION, INSTITUTION
- REPORT, NOTIFICATION, MESSAGE
- AI_SERVICE

### Permission Rules

- Permissions are globally unique
- Granular and reusable
- Assigned through roles

---

## 5. Authentication Standards

### Supported Methods

- Email + Password
- JWT Access Tokens
- Refresh Tokens
- Session Management

### Password Requirements

- Minimum 8 characters
- BCrypt hashing
- No plaintext storage
- Expiration policies

### Account Security

- Account lock after 5 failed attempts
- Session revocation support
- Concurrent session limits

---

## 6. Authorization Standards

### RBAC Architecture

```
User -> Roles -> Permissions -> Policies -> Access
```

### Authorization Flow

1. User authenticates
2. Session validated
3. Roles checked
4. Permissions validated
5. Access granted/denied

### Rules

- No bypass mechanisms
- No hardcoded permissions
- No cross-domain authentication

---

## 7. Session Standards

### Session Fields

- tokenHash (unique)
- refreshTokenHash
- ipAddress
- userAgent
- deviceType
- expiresAt
- refreshExpiresAt
- lastActivityAt
- isActive
- revokedAt, revokedReason

### Session Rules

- Expired sessions cleaned up hourly
- Revocation support
- Activity tracking

---

## 8. API Standards

### Endpoint Structure

```
/api/v1/identity/
  /authentication
    POST /login
    POST /logout
    POST /refresh
  /users
    GET /
    GET /{id}
    POST /
    PUT /{id}
    DELETE /{id}
  /roles
    GET /
    GET /{id}
    POST /
    PUT /{id}
    DELETE /{id}
  /permissions
    GET /
    GET /{id}
    POST /
  /sessions
    GET /me
    DELETE /{id}
    DELETE /me/all
```

---

## 9. Security Standards

### Required

- BCrypt password hashing
- JWT with refresh tokens
- Session isolation
- Rate limiting
- Audit support
- Account lock policies

### Prohibited

- Plaintext credentials
- Role bypass mechanisms
- Session bypass mechanisms
- Hardcoded permissions

---

## 10. Identity Directory Structure

```
backend/identity/
  controllers/
  services/
  repositories/
  entities/
  dto/
  validators/
  events/
  security/
  sessions/
  permissions/
  roles/
  configurations/
  tests/
```
