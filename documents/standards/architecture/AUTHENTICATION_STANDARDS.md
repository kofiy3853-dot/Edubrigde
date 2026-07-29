# Authentication Standards

## LOCKED STANDARDS

These standards are permanently locked and cannot be modified.

---

## 1. Zero-Trust Architecture

Every request must be authenticated, validated, authorized, traced, and monitored. Authentication is a continuous security process, not a one-time event.

---

## 2. JWT Standards

### Token Types

| Type | Lifetime | Purpose |
|------|----------|---------|
| Access Token | 15 minutes | API access |
| Refresh Token | 7 days | Token rotation |
| Verification Token | 24 hours | Email verification |
| Password Reset Token | 1 hour | Password recovery |

### Token Rules

- Access tokens are short-lived (15 minutes max)
- Refresh tokens rotate on every use
- Old refresh tokens are revoked on rotation
- Token validation occurs on every request
- Tokens are hashed (SHA-256) before storage
- Unlimited token validity is prohibited

### JWT Claims

```json
{
  "sub": "user-id",
  "email": "user@example.com",
  "iss": "edubridge-os",
  "iat": "2026-01-01T00:00:00Z",
  "exp": "2026-01-01T00:15:00Z",
  "type": "access|refresh"
}
```

---

## 3. Refresh Token Standards

### Rotation Rules

- Mandatory token rotation on every refresh
- Old token revoked immediately
- New token generated
- Session updated with new token hash

### Expiration

- Refresh tokens expire after 7 days
- Refresh tokens are revoked on logout
- All refresh tokens revoked on password change

---

## 4. Session Standards

### Support

- Single device sessions
- Multiple concurrent sessions
- Session revocation
- Concurrent session policies
- Expiration policies
- Activity monitoring

### Session Policies

| User Type | Max Sessions |
|-----------|--------------|
| Student | 5 |
| Consultant | 3 |
| Administrator | 2 |
| Super Admin | 1 |

---

## 5. Account Verification Standards

### Workflow

1. User registers
2. Verification email sent
3. Verification token generated (24h expiry)
4. User clicks verification link
5. Token validated
6. Account marked as verified
7. Authentication enabled

### Rules

- Unverified accounts cannot access protected resources
- Verification tokens are single-use
- Tokens expire after 24 hours
- Audit logging required

---

## 6. Password Recovery Standards

### Workflow

1. User requests password reset
2. Rate-limited token generation (1h expiry)
3. Reset email sent
4. User clicks reset link
5. Token validated
6. Password updated
7. All sessions revoked
8. Audit logging required

### Rules

- Rate limiting on reset requests
- Tokens expire after 1 hour
- Single-use tokens
- All sessions revoked after reset

---

## 7. Security Standards

### Required

- BCrypt password hashing
- JWT with refresh tokens
- Session isolation
- Rate limiting
- Token rotation
- Account lock policies
- Audit logging
- Security monitoring

### Prohibited

- Plaintext credentials
- Unlimited token validity
- Shared refresh tokens
- Session bypass mechanisms
- Token reuse after rotation
- Unverified account access
- Authentication without monitoring

---

## 8. API Standards

### Endpoints

```
/api/v1/identity/authentication/
  POST /register
  POST /login
  POST /refresh
  POST /logout
  POST /verify-account
  POST /forgot-password
  POST /reset-password
```

### Response Format

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "accessToken": "...",
    "refreshToken": "...",
    "tokenType": "Bearer",
    "expiresIn": 900,
    "user": { ... }
  }
}
```

---

## 9. Authentication Directory Structure

```
identity/
  security/
    JWTConfigProperties.java
    JWTTokenProvider.java
    JWTAuthenticationFilter.java
  services/
    AuthenticationService.java
  controllers/
    AuthenticationController.java
  entities/
    VerificationToken.java
  repositories/
    VerificationTokenRepository.java
```

---

## 10. Testing Standards

### Required Tests

- Login tests
- Logout tests
- Refresh token tests
- Session tests
- Password recovery tests
- Verification tests
- Security tests
- Integration tests

### Inherited Qualities

- Security
- Validation
- Performance
- Observability
- Maintainability
- Scalability
