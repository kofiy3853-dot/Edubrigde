# Testing Standards

## Status

LOCKED - Sprint 0A, Step 06

## Rule

Testing is mandatory. No feature is complete without comprehensive tests.

## Testing Philosophy

```
Build Feature -> Test -> Secure -> Monitor -> Review -> Approve -> Deploy
```

Never:

```
Build Feature -> Deploy -> Test Later
```

## Test Types

### 1. Unit Testing

Test individual functions and methods in isolation.

```
Scope:      Single function or method
Tools:      JUnit (Java), Vitest (TypeScript)
Speed:      Fast (< 100ms per test)
Mocking:    External dependencies mocked
Coverage:   Business logic 100%
```

### 2. Integration Testing

Test interactions between components.

```
Scope:      Multiple components working together
Tools:      JUnit + TestContainers, Vitest + MSW
Speed:      Moderate (< 5s per test)
Mocking:    Infrastructure mocked, domain logic real
Coverage:   Service + Repository integration
```

### 3. Component Testing

Test UI components in isolation.

```
Scope:      Single React component
Tools:      Vitest + React Testing Library
Speed:      Fast (< 200ms per test)
Mocking:    API calls mocked
Coverage:   All user-facing components
```

### 4. API Testing

Test API endpoints against contract.

```
Scope:      HTTP request/response cycle
Tools:      Playwright, Vitest
Speed:      Moderate (< 2s per test)
Mocking:    Database mocked
Coverage:   All API endpoints
```

### 5. Database Testing

Test database operations and migrations.

```
Scope:      Schema, migrations, queries
Tools:      TestContainers, JUnit
Speed:      Slow (< 10s per test)
Mocking:    None (real database)
Coverage:   All migrations, all queries
```

### 6. Security Testing

Test for vulnerabilities.

```
Scope:      Authentication, authorization, injection
Tools:      OWASP ZAP, SonarQube
Speed:      Variable
Mocking:    None
Coverage:   All security-sensitive code
```

### 7. Performance Testing

Test response times and throughput.

```
Scope:      API response times, bundle size
Tools:      Lighthouse, k6
Speed:      Variable
Mocking:    None
Coverage:   Critical user journeys
```

### 8. Accessibility Testing

Test WCAG 2.1 AA compliance.

```
Scope:      All UI components
Tools:      axe-core, Lighthouse
Speed:      Fast
Mocking:    None
Coverage:   All user-facing pages
```

### 9. End-to-End Testing

Test complete user journeys.

```
Scope:      Full application flow
Tools:      Playwright
Speed:      Slow (< 30s per test)
Mocking:    None (real application)
Coverage:   Critical user journeys
```

## Coverage Requirements

| Area | Requirement |
|------|-------------|
| Business Logic | 100% |
| Authentication | 100% |
| Authorization | 100% |
| API Contracts | 100% |
| Utilities | 100% |
| UI Components | Required |
| Database Queries | Required |

## Test Naming Convention

```
{method} {condition} {expected result}

Examples:
  login with valid credentials returns token
  login with invalid password returns 401
  login with non-existent user returns 401
  getUniversity by id returns university
  getUniversity by id with invalid id returns 404
```

## Test Structure

```java
@Test
void login_withValidCredentials_returnsToken() {
    // Arrange
    User user = createTestUser();

    // Act
    LoginResponse response = authService.login(credentials);

    // Assert
    assertNotNull(response.getToken());
    assertEquals(user.getId(), response.getUser().getId());
}
```

## Test File Location

```
Unit tests:        Co-located with source or in tests/
Integration tests: tests/backend/
E2E tests:         tests/e2e/
Frontend tests:    tests/frontend/
Database tests:    tests/database/
Security tests:    tests/security/
```

## Test Execution

```
CI/CD Pipeline:
  1. Unit tests (always)
  2. Integration tests (always)
  3. API tests (always)
  4. Database tests (always)
  5. Security tests (always)
  6. E2E tests (on merge to develop)
  7. Performance tests (on release)
```

## Never

```
- Skip tests
- Write tests after deployment
- Mock everything
- Test implementation details
- Skip edge cases
- Skip error cases
- Skip security tests
```
