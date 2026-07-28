# Development Principles

## Status

LOCKED - Sprint 0A, Step 04

## Principles

Every EduBridge OS implementation must follow these non-negotiable principles:

### 1. Single Responsibility Principle

Each module, class, function, and component has one reason to change.

```
GOOD: UniversitySearchService handles university search only.
BAD: UniversityService handles search, payments, and notifications.
```

### 2. Separation of Concerns

Different layers handle different responsibilities.

```
Controller  -> Handles HTTP request/response
Service     -> Business logic
Repository  -> Data access
Model       -> Domain entities
DTO         -> Data transfer objects
```

### 3. Domain Driven Design

Code structure mirrors business domains.

```
backend/
  identity/      -> Auth, users, roles
  academic/      -> Students, courses, grades
  career/        -> Assessments, matching
  financial/     -> Payments, scholarships
  institution/   -> Universities, programs
```

### 4. Composition over Inheritance

Prefer composable units over deep inheritance chains.

```
GOOD: Compose validators, formatters, and handlers.
BAD: Deep class hierarchies with 5+ levels.
```

### 5. Reusable Components

Build for reuse within the platform.

```
packages/
  ui/          -> Shared UI components
  types/       -> Shared type definitions
  validators/  -> Shared validation schemas
  utils/       -> Shared utilities
```

### 6. API First Development

Define contracts before implementation.

```
1. Write OpenAPI spec
2. Generate types from spec
3. Implement backend
4. Implement frontend against contract
```

### 7. Contract First Development

Interfaces define boundaries between modules.

```
Frontend <- API Contract -> Backend
Service A <- Event Contract -> Service B
Module A <- Interface -> Module B
```

### 8. Event Driven Architecture

Use events for cross-domain communication.

```
UserRegistered -> triggers -> WelcomeEmail
PaymentCompleted -> triggers -> EnrollmentUpdate
UniversityMatched -> triggers -> NotificationSend
```

### 9. Platform First Development

Build the platform before features.

```
1. Establish standards (Sprint 0A)
2. Build infrastructure (Sprint 0B)
3. Build identity domain (Sprint 1)
4. Build features on top
```

## Violations

Any implementation that violates these principles must be refactored before approval.
