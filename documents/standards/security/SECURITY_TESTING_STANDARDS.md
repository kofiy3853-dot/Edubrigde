# Security Testing Standards

## Status

LOCKED - Sprint 0A, Step 06

## Rule

Security is tested at every layer. No feature ships without security review.

## Security Testing Layers

### 1. Authentication Testing

```
Test Cases:
  - Login with valid credentials succeeds
  - Login with invalid credentials fails
  - Login with non-existent user fails
  - Password hashing is secure (bcrypt)
  - JWT tokens expire correctly
  - Refresh tokens work correctly
  - MFA challenge works correctly
  - Account lockout triggers correctly
  - Password reset flow is secure
```

### 2. Authorization Testing

```
Test Cases:
  - Unauthenticated users cannot access protected routes
  - Students cannot access admin endpoints
  - Admins cannot modify other users' data
  - Role-based access control works correctly
  - Permission-based access works correctly
  - Resource-level authorization works
```

### 3. Input Validation Testing

```
Test Cases:
  - SQL injection attempts are blocked
  - XSS attempts are blocked
  - Path traversal attempts are blocked
  - Command injection attempts are blocked
  - LDAP injection attempts are blocked
  - XML injection attempts are blocked
  - Invalid input formats rejected
  - Input length limits enforced
```

### 4. Output Validation Testing

```
Test Cases:
  - Error messages don't leak internals
  - Stack traces not exposed
  - Database errors not exposed
  - Internal paths not exposed
  - Sensitive data masked in responses
```

### 5. API Security Testing

```
Test Cases:
  - Rate limiting works correctly
  - CORS headers correct
  - Security headers present
  - HTTPS enforced
  - API versioning works
  - Content-Type validation works
```

### 6. Data Protection Testing

```
Test Cases:
  - Passwords hashed correctly
  - Sensitive data encrypted at rest
  - Sensitive data encrypted in transit
  - PII masked in logs
  - Tokens not logged
  - Secrets not in code
```

## OWASP Top 10 Checklist

| Risk | Test Required |
|------|---------------|
| A01 Broken Access Control | Yes |
| A02 Cryptographic Failures | Yes |
| A03 Injection | Yes |
| A04 Insecure Design | Yes |
| A05 Security Misconfiguration | Yes |
| A06 Vulnerable Components | Yes |
| A07 Auth Failures | Yes |
| A08 Data Integrity Failures | Yes |
| A09 Logging Failures | Yes |
| A10 SSRF | Yes |

## Security Test Tools

```
Static Analysis:   SonarQube, ESLint security plugins
Dynamic Analysis:  OWASP ZAP
Dependency Scan:   npm audit, Snyk
Code Review:       Manual security review
```

## Security Review Process

```
1. Developer runs security tests
2. SonarQube scan runs
3. Dependency audit runs
4. Manual security review (for sensitive features)
5. Security architect approval (for critical features)
```

## Never

```
- Skip security testing
- Skip dependency audit
- Skip OWASP review
- Skip penetration testing for critical features
- Expose test credentials
- Run security tests with production data
```
