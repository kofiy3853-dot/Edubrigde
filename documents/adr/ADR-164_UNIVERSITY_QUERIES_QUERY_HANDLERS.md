# ADR-164: University Queries & Query Handlers

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Queries & Query Handlers
**Decision:** University Queries & Query Handlers

## Context

EduBridge OS requires a structured read-side architecture for the University bounded context. Without Queries and Query Handlers, read operations would lack separation from write-side business logic, reducing testability and scalability.

## Decision

The University bounded context adopts Queries and Query Handlers as the read-side implementation of CQRS. Queries retrieve information without modifying domain state. Query Handlers execute read operations against optimized read models and projections rather than the write-side aggregate.

## Consequences

### Positive

- Clear CQRS separation between read and write operations
- Immutable queries ensure traceability
- Read models optimized for retrieval
- Technology-independent design enables flexibility
- Independently testable services

### Negative

- Query proliferation requires governance
- Read model maintenance adds complexity
- Indirection between client and data source

## Scope

### Owned
- University discovery queries
- University profile queries
- Academic program queries
- Faculty queries
- Campus queries
- Admission requirement queries
- Tuition structure queries
- Intake period queries
- Accreditation queries
- Ranking queries

### NOT Owned
- Command operations (write side)
- Write-side business logic
- Aggregate repositories
- Presentation-layer components

## Query Catalog
- GetUniversityByIdQuery
- SearchUniversitiesQuery
- GetUniversityProfileQuery
- GetAcademicProgramsQuery
- GetCampusDirectoryQuery
- GetAdmissionRequirementsQuery
- GetTuitionInformationQuery
- GetIntakeScheduleQuery
- GetAccreditationQuery
- CompareUniversitiesQuery

## Implementation Rules
1. Queries are read-only
2. Queries are immutable
3. One query maps to one handler
4. Query Handlers are stateless
5. Query Handlers never modify state
6. Read models are projection-based
7. Business rules remain in the domain
8. Queries are technology-independent
9. CQRS read/write separation is preserved
10. Read-side performance is prioritized

## NOT ALLOWED
- Queries modifying domain state
- Query Handlers executing commands
- Query Handlers implementing business logic
- Query Handlers accessing write repositories
- Business rule evaluation during query execution
