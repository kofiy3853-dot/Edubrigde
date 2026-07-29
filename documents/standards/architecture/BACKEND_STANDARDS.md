# Backend Standards

## Status

LOCKED - Sprint 0C, Step 14

## Principle

Modular monolith. Domain isolation. No coupling.

## Technology Stack

```
Java            21 LTS
Spring Boot     3.4.x
Gradle          8.12
Lombok          Latest
MapStruct       1.6.x
SpringDoc       2.8.x
JUnit           5.x
Mockito         5.x
```

## Module Structure

```
backend/
  core/           -> Application entry point
  shared/         -> Contracts, exceptions, DTOs
  identity/       -> Authentication, authorization
  academic/       -> Universities, programs, courses
  career/         -> Job matching, applications
  financial/      -> Payments, budgets, scholarships
  institution/    -> University management
  communication/  -> Notifications, messaging
  analytics/      -> Reporting, insights
  ai/             -> Recommendations, ML
  integration/    -> Cross-domain orchestration
  tests/          -> Integration tests
```

## Domain Module Structure

```
domain/
  controllers/     -> REST endpoints
  services/        -> Business logic
  repositories/    -> Data access
  entities/        -> JPA entities
  dto/             -> Request/Response objects
  validators/      -> Input validation
  exceptions/      -> Domain exceptions
  events/          -> Domain events
  tests/           -> Unit tests
  configurations/  -> Bean configuration
```

## Layer Rules

```
Controller:
  - Handle HTTP requests
  - Validate input
  - Return ResponseEntity
  - NO business logic

Service:
  - Implement business logic
  - Use repositories
  - Transactional operations
  - NO HTTP handling

Repository:
  - Data access only
  - JPA queries only
  - NO business logic

Entity:
  - JPA mapping only
  - No HTTP concepts
  - No business logic
```

## API Standards

```
Base URL:       /api/v1
Versioning:     Path-based (/api/v1/{domain})
Format:         REST JSON
Documentation:  OpenAPI/Swagger
Validation:     Jakarta Validation
```

## Dependency Rules

```
Modules depend on:
  - shared (contracts, exceptions, DTOs)
  - Spring Framework

Modules do NOT depend on:
  - Other domain modules
  - Infrastructure details
  - External services
```

## Java Standards

```
1. Constructor injection (NO field injection)
2. Immutable objects preferred
3. Records for DTOs
4. Sealed classes for enums
5. Pattern matching where appropriate
6. Strong typing throughout
```

## Testing Standards

```
Unit Tests:         Service layer, validators, utilities
Integration Tests:  Controller layer, repository layer
Contract Tests:     API contracts between modules
Performance Tests:  API response times
```

## Never

```
- Business logic in controllers
- Cross-domain repositories
- Circular dependencies
- God services
- Unversioned APIs
- Domain coupling
- Field injection
- Static business logic
- Shared mutable state
```
