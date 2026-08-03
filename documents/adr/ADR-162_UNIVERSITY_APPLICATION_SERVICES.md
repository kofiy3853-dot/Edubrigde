# ADR-162: University Application Services

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Application Services
**Decision:** University Application Services

## Context

EduBridge OS requires an orchestration layer between external interfaces and the University Domain Model. Without Application Services, use case orchestration would be embedded in controllers or domain logic, reducing testability and separation of concerns.

## Decision

The University bounded context adopts Application Services as the orchestration layer between external interfaces (REST APIs, GraphQL, messaging, scheduled jobs, etc.) and the University Domain Model. Application Services coordinate use cases but do not contain business rules.

## Consequences

### Positive

- Clear separation between orchestration and business logic
- Stateless services enable scalability
- Transaction-aware design ensures consistency
- Technology-independent design enables flexibility
- Independently testable services

### Negative

- Service proliferation requires governance
- Transaction boundary management adds complexity
- Indirection between external interfaces and domain

## Scope

### Owned
- University registration orchestration
- University profile management
- Program management orchestration
- Admission requirement management
- Tuition management orchestration
- Campus management orchestration
- Accreditation management
- University publication orchestration

### NOT Owned
- Business rules (owned by Domain Services, Policies, Aggregates)
- Persistence logic (owned by Repositories)
- API logic (owned by Controllers)
- Presentation logic

## Application Service Catalog
- UniversityRegistrationApplicationService
- UniversityProfileApplicationService
- ProgramManagementApplicationService
- AdmissionRequirementApplicationService
- TuitionManagementApplicationService
- CampusManagementApplicationService
- AccreditationApplicationService
- UniversityPublicationApplicationService

## Implementation Rules
1. Application Services orchestrate use cases
2. Business rules remain in the domain layer
3. Services are stateless
4. One transaction boundary per use case
5. Repository access occurs through interfaces
6. Domain events originate from aggregates
7. Infrastructure dependencies are abstracted
8. Services are independently testable
9. Ubiquitous language is preserved
10. Domain integrity is maintained

## NOT ALLOWED
- Business rules in Application Services
- Direct database access
- Presentation logic in Application Services
- Authorization decisions beyond invoking domain policies
- Domain event creation by Application Services
