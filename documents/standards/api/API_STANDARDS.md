# API Standards

## Status

LOCKED - Sprint 0A, Step 05

## Rule

Every API is contract-first. No implementation without a defined contract.

## Principles

```
Contract First    -> Define before implementing
Version First     -> Version from day one
Validation First  -> Validate at boundaries
Security First    -> Secure by default
Documentation First -> Document before deploying
Testing First     -> Test against contract
```

## API Structure

All APIs follow RESTful conventions under versioned prefix:

```
/api/v1/{domain}/{resource}
```

### Domain Prefixes

```
/api/v1/identity/       -> Auth, users, roles, profiles
/api/v1/academic/       -> Students, courses, grades
/api/v1/career/         -> Assessments, matching
/api/v1/financial/      -> Payments, scholarships
/api/v1/institution/    -> Universities, programs
/api/v1/communication/  -> Notifications, emails
/api/v1/analytics/      -> Reports, metrics
/api/v1/ai/             -> Recommendations, predictions
```

### Examples

```
POST   /api/v1/identity/login
POST   /api/v1/identity/register
GET    /api/v1/identity/me
PUT    /api/v1/identity/profile

GET    /api/v1/institution/universities
GET    /api/v1/institution/universities/:id
GET    /api/v1/institution/universities/search

POST   /api/v1/financial/payments
GET    /api/v1/financial/payments/:id
GET    /api/v1/financial/payments/history
```

## Required Contract Elements

Every endpoint must define:

```
1. HTTP Method (GET, POST, PUT, PATCH, DELETE)
2. Path
3. Description
4. Authentication required (yes/no)
5. Authorization roles/permissions
6. Request headers
7. Request path parameters
8. Request query parameters
9. Request body schema
10. Response body schema (success)
11. Error response schemas (4xx, 5xx)
12. Rate limits
13. Example request/response
```

## OpenAPI Specification

Every API domain must provide OpenAPI 3.0 specification.

Location: `documents/apis/{domain}/openapi.yaml`

```yaml
openapi: 3.0.3
info:
  title: EduBridge OS - Identity API
  version: 1.0.0
paths:
  /api/v1/identity/login:
    post:
      summary: User login
      tags: [Authentication]
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/LoginRequest'
      responses:
        '200':
          description: Login successful
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/LoginResponse'
        '401':
          description: Invalid credentials
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/ErrorResponse'
```

## Request Contract

```json
{
  "method": "POST",
  "path": "/api/v1/identity/login",
  "headers": {
    "Content-Type": "application/json"
  },
  "body": {
    "email": "string (required, email format)",
    "password": "string (required, min 8 chars)"
  }
}
```

## Response Contract

### Success Response

```json
{
  "status": 200,
  "body": {
    "token": "string",
    "user": {
      "id": "string (UUID)",
      "email": "string",
      "firstName": "string",
      "lastName": "string",
      "role": "STUDENT | ADMIN"
    }
  }
}
```

### Error Response

```json
{
  "status": 400,
  "body": {
    "error": "string",
    "code": "string",
    "timestamp": "string (ISO 8601)",
    "traceId": "string",
    "details": []
  }
}
```

## HTTP Methods

| Method | Purpose | Idempotent |
|--------|---------|------------|
| GET | Read resource | Yes |
| POST | Create resource | No |
| PUT | Full update | Yes |
| PATCH | Partial update | Yes |
| DELETE | Remove resource | Yes |

## Status Codes

| Code | Meaning |
|------|---------|
| 200 | Success |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict |
| 422 | Unprocessable Entity |
| 429 | Rate Limited |
| 500 | Internal Server Error |

## Validation Rules

Every request must be validated at the controller boundary:

```
1. Validate path parameters (type, format)
2. Validate query parameters (type, presence)
3. Validate request body (schema, required fields)
4. Validate business rules (in service layer)
5. Return 400 for validation errors
```

## Security Requirements

```
1. HTTPS required in production
2. Authentication via JWT Bearer token
3. Authorization enforced per endpoint
4. Rate limiting applied globally
5. CORS properly configured
6. Input sanitization at boundary
```

## Never

```
- Return stack traces to clients
- Expose internal database errors
- Skip validation
- Skip authentication
- Skip authorization
- Version via query params (use path)
- Use plural nouns for collections
- Use verbs in endpoints
```
