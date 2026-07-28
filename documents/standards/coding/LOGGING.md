# Logging Standards

## Status

LOCKED - Sprint 0A, Step 04

## Rule

Every significant action must be logged. Sensitive data must never be logged.

## Log Levels

```
ERROR   -> System errors requiring immediate attention
WARN    -> Potential issues that need monitoring
INFO    -> Significant business events
DEBUG   -> Detailed technical information (development only)
TRACE   -> Extremely detailed tracing (development only)
```

## Required Log Categories

### 1. Application Logs

System lifecycle events.

```
- Application started
- Application stopped
- Configuration loaded
- Database connected
- Cache connected
```

### 2. Authentication Logs

Security-critical auth events.

```
- User login success
- User login failure
- User logout
- Password changed
- Account locked
- Token refreshed
- Token expired
- MFA challenge
```

### 3. Audit Logs

Business-critical data changes.

```
- User created
- User updated
- User deleted
- Profile updated
- Payment processed
- University added
- Permission changed
```

### 4. Security Logs

Security-relevant events.

```
- Rate limit exceeded
- Invalid access attempt
- Permission denied
- Suspicious activity
- IP blocked
- Brute force detected
```

### 5. API Logs

Request/response logging.

```
- Request received
- Request processed
- Response sent
- Request duration
- Error response
```

### 6. Database Logs

Data access events.

```
- Query executed
- Migration started
- Migration completed
- Connection pool status
- Slow query detected
```

### 7. Performance Logs

Performance metrics.

```
- Response time
- Memory usage
- CPU usage
- Request throughput
- Error rate
```

## Log Format

Structured JSON format:

```json
{
  "timestamp": "2026-01-01T00:00:00.000Z",
  "level": "INFO",
  "category": "authentication",
  "message": "User login successful",
  "context": {
    "userId": "uuid",
    "email": "user@example.com",
    "ip": "192.168.1.1",
    "userAgent": "Mozilla/5.0..."
  },
  "traceId": "abc-123",
  "spanId": "def-456"
}
```

## What NEVER to Log

```
NEVER LOG

Passwords
Secrets
API keys
JWT tokens
Credit card numbers
Social security numbers
Health information
Private keys
Session tokens
```

## Where Logs Go

| Environment | Destination |
|-------------|-------------|
| Development | Console |
| Staging | Structured files + external service |
| Production | OpenTelemetry -> Grafana |

## Retention

| Log Type | Retention |
|----------|-----------|
| Application | 30 days |
| Authentication | 90 days |
| Audit | 1 year |
| Security | 1 year |
| Performance | 30 days |
