# ADR-146: Student Query-Based Read Model

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Queries and Query Handlers
**Decision:** Student Query-Based Read Model

## Context

EduBridge OS requires a structured read-side architecture for the Student bounded context. Without a query-based model, read operations would lack separation from write-side business logic, reducing testability and scalability.

## Decision

The Student bounded context adopts a Query-Based Read Model. Queries represent requests to retrieve Student information without changing business state. Query Handlers coordinate read operations using read models while remaining independent of write-side business logic.

## Consequences

### Positive

- Clear CQRS separation between read and write operations
- Immutable queries ensure traceability
- Read models optimized for retrieval without aggregate modification
- Transport-independent design enables flexibility
- Query contracts are versionable and testable

### Negative

- Query proliferation requires governance
- Read model maintenance adds overhead
- Indirection between client and data source

## Scope

### Owned
- Student profile queries
- Academic profile queries
- Education history queries
- Student goal queries
- Learning preference queries
- Student lifecycle queries

### NOT Owned
- Write operations (owned by Commands and Command Handlers)
- Domain event publication
- Business mutations
- Infrastructure retrieval

## Query Catalog
- GetStudentProfileQuery
- GetStudentContactQuery
- GetStudentSummaryQuery
- GetAcademicProfileQuery
- GetEducationHistoryQuery
- GetAcademicMilestonesQuery
- GetStudentGoalsQuery
- GetStudentGoalDetailsQuery
- GetCompletedGoalsQuery
- GetLearningPreferencesQuery
- GetAccessibilityPreferencesQuery
- GetStudyScheduleQuery
- GetStudentStatusQuery
- GetStudentTimelineQuery

## Implementation Rules
1. Queries are immutable
2. Queries never modify business state
3. Query Handlers coordinate retrieval only
4. Read Models are optimized for reading
5. Structural validation precedes execution
6. Domain events are never published during queries
7. Query contracts are versioned
8. Infrastructure remains abstracted
9. CQRS separation is preserved
10. Read-side operations remain side-effect free

## NOT ALLOWED
- Queries modifying aggregate state
- Query Handlers executing commands
- Query Handlers publishing domain events
- Query Handlers persisting data
- Cross-domain query access
