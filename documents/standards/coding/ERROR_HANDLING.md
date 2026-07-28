# Error Handling Standards

## Status

LOCKED - Sprint 0A, Step 04

## Rule

No silent failures. Every error must be handled, logged, and reported.

## Error Categories

### 1. Validation Errors (400)

Invalid input from the client.

```json
{
  "error": "Validation failed",
  "code": "VALIDATION_ERROR",
  "status": 400,
  "timestamp": "2026-01-01T00:00:00Z",
  "traceId": "abc-123"
}
```

### 2. Authentication Errors (401)

Missing or invalid credentials.

```json
{
  "error": "Authentication required",
  "code": "AUTHENTICATION_ERROR",
  "status": 401,
  "timestamp": "2026-01-01T00:00:00Z",
  "traceId": "abc-123"
}
```

### 3. Authorization Errors (403)

Valid credentials but insufficient permissions.

```json
{
  "error": "Insufficient permissions",
  "code": "AUTHORIZATION_ERROR",
  "status": 403,
  "timestamp": "2026-01-01T00:00:00Z",
  "traceId": "abc-123"
}
```

### 4. Not Found Errors (404)

Resource does not exist.

```json
{
  "error": "Resource not found",
  "code": "NOT_FOUND_ERROR",
  "status": 404,
  "timestamp": "2026-01-01T00:00:00Z",
  "traceId": "abc-123"
}
```

### 5. Business Errors (422)

Valid request but violates business rules.

```json
{
  "error": "Email already registered",
  "code": "BUSINESS_ERROR",
  "status": 422,
  "timestamp": "2026-01-01T00:00:00Z",
  "traceId": "abc-123"
}
```

### 6. Infrastructure Errors (500)

Internal system failure.

```json
{
  "error": "Internal server error",
  "code": "INFRASTRUCTURE_ERROR",
  "status": 500,
  "timestamp": "2026-01-01T00:00:00Z",
  "traceId": "abc-123"
}
```

### 7. Rate Limit Errors (429)

Too many requests.

```json
{
  "error": "Rate limit exceeded",
  "code": "RATE_LIMIT_ERROR",
  "status": 429,
  "retryAfter": 60,
  "timestamp": "2026-01-01T00:00:00Z",
  "traceId": "abc-123"
}
```

## Error Response Fields

Every error response must include:

| Field | Required | Description |
|-------|----------|-------------|
| error | Yes | Human-readable message |
| code | Yes | Machine-readable error code |
| status | Yes | HTTP status code |
| timestamp | Yes | ISO 8601 timestamp |
| traceId | Yes | Request trace identifier |
| details | No | Additional error context |

## Error Handling Rules

```
1. Never expose internal error details to clients
2. Never log passwords, tokens, or secrets
3. Always include trace ID for debugging
4. Always return consistent error format
5. Always log errors before returning response
6. Never swallow exceptions silently
7. Always provide recovery path when possible
```

## Frontend Error Handling

```
1. Display user-friendly error messages
2. Log technical errors to monitoring
3. Provide retry options where appropriate
4. Never show stack traces to users
5. Handle network errors gracefully
6. Handle loading state errors
```
