# Validation Standards

## Status

LOCKED - Sprint 0A, Step 04

## Rule

No data enters or leaves the system without validation.

## Validation Layers

### 1. Input Validation

Validate all external input at system boundaries.

```
Frontend:
  - Form fields validated before submission
  - Email format validation
  - Password strength validation
  - Required field validation

Backend:
  - Request body validation (Zod/Joi)
  - Query parameter validation
  - Path parameter validation
  - Header validation
```

### 2. Output Validation

Validate data before returning to clients.

```
Backend:
  - Response schema validation
  - Type safety checks
  - Sanitization of sensitive fields

Frontend:
  - API response type checking
  - Runtime type guards
```

### 3. Data Validation

Validate data at the database layer.

```
Database:
  - NOT NULL constraints
  - UNIQUE constraints
  - CHECK constraints
  - Foreign key constraints
  - Type constraints
```

### 4. Business Rule Validation

Validate domain-specific rules.

```
Examples:
  - Email must be unique per user
  - Graduation year must be in the future
  - Payment amount must be positive
  - University programs must exist
```

### 5. Configuration Validation

Validate environment and configuration.

```
On application start:
  - DATABASE_URL must be set
  - JWT_SECRET must be set
  - PORT must be valid number
  - All required env vars present
```

## Validation Error Format

Every validation error must return:

```json
{
  "error": "Validation failed",
  "code": "VALIDATION_ERROR",
  "details": [
    {
      "field": "email",
      "message": "Must be a valid email address",
      "value": "invalid-email"
    }
  ],
  "timestamp": "2026-01-01T00:00:00Z"
}
```

## Where Validation Applies

| Layer | Type | Required |
|-------|------|----------|
| Frontend | Input | Yes |
| API Gateway | Rate limit | Yes |
| Controller | Request body | Yes |
| Controller | Query params | Yes |
| Service | Business rules | Yes |
| Repository | Data integrity | Yes |
| Database | Constraints | Yes |
| Frontend | Response | Yes |

## Never Skip

Validation is never optional. No exceptions.
