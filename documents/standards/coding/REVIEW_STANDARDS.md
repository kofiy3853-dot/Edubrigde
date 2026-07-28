# Review Standards

## Status

LOCKED - Sprint 0A, Step 04

## Rule

No implementation is approved without passing all required reviews.

## Review Types

### 1. Architecture Review

Verify alignment with system architecture.

```
Checklist:
  - Follows domain boundaries
  - Follows separation of concerns
  - Follows API-first development
  - Follows event-driven patterns
  - No cross-domain coupling
  - Appropriate technology choices
  - Scalability considerations addressed
```

### 2. Code Review

Verify code quality and standards.

```
Checklist:
  - Follows naming conventions
  - Follows coding principles
  - No code duplication
  - Clear and readable
  - Proper error handling
  - Proper logging
  - No hardcoded values
  - No magic numbers/strings
  - Comments explain why, not what
  - Functions are focused and small
```

### 3. Security Review

Verify security requirements.

```
Checklist:
  - Authentication enforced
  - Authorization enforced
  - Input validation present
  - Output encoding applied
  - No secrets in code
  - Rate limiting applied
  - Audit logging present
  - Error messages safe
  - OWASP Top 10 addressed
```

### 4. Testing Review

Verify test coverage and quality.

```
Checklist:
  - Unit tests present
  - Integration tests present
  - Edge cases covered
  - Error cases covered
  - Tests are meaningful (not just coverage)
  - Tests are maintainable
  - Tests run in CI/CD
```

### 5. Documentation Review

Verify documentation completeness.

```
Checklist:
  - Module README updated
  - API docs updated
  - Database docs updated
  - Examples provided
  - Future improvements noted
  - Documentation is accurate
```

### 6. Performance Review

Verify performance requirements.

```
Checklist:
  - No performance regression
  - Response times acceptable
  - Caching strategy appropriate
  - Pagination implemented
  - Bundle size acceptable
  - Memory usage acceptable
```

### 7. Verification Review

Verify all checks pass.

```
Checklist:
  - All tests passing
  - CI/CD pipeline green
  - No linting errors
  - No type errors
  - Build succeeds
  - Manual testing completed
```

## Review Assignment

| Review Type | Required Reviewer |
|-------------|-------------------|
| Architecture | System Architect |
| Code | Any senior developer |
| Security | Security Architect |
| Testing | QA Engineer |
| Documentation | Tech Writer or Author |
| Performance | Performance Engineer |

## Approval Requirements

```
- All review types must be passed
- All feedback must be addressed
- All discussions must be resolved
- All checks must be green
```

## No Bypassing

No review may be bypassed, expedited, or waived. Every review is mandatory.
