# ADR-165: University Read Models & Projections

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Read Models & Projections
**Decision:** University Read Models & Projections

## Context

EduBridge OS requires a structured read-side implementation for the University bounded context. Without Read Models and Projections, query operations would lack optimized representations, reducing performance and scalability.

## Decision

The University bounded context adopts Read Models and Projections as the read-side implementation of CQRS. Read Models provide query-optimized representations of university information. Projections consume University Domain Events and maintain these models independently of the write-side aggregate.

## Consequences

### Positive

- Query-optimized data representations for fast retrieval
- Event-driven projections ensure eventual consistency
- Independent read/write sides improve scalability
- Deterministic projections enable rebuildability
- Idempotent projections support fault tolerance

### Negative

- Eventual consistency requires careful boundary management
- Projection maintenance adds operational complexity
- Read model proliferation requires governance

## Scope

### Owned
- University summary projections
- University profile projections
- Academic program projections
- Campus projections
- Tuition projections
- Admission requirement projections
- Intake calendar projections
- Accreditation projections
- University comparison projections

### NOT Owned
- Write-side aggregates
- Write-side repositories
- Infrastructure messaging technologies
- Domain event publishing

## Read Model Catalog
- UniversitySummaryReadModel
- UniversityProfileReadModel
- AcademicProgramCatalogReadModel
- CampusDirectoryReadModel
- TuitionCatalogReadModel
- AdmissionRequirementsReadModel
- IntakeCalendarReadModel
- AccreditationOverviewReadModel
- UniversityComparisonReadModel

## Projection Catalog
- UniversitySummaryProjection
- UniversityProfileProjection
- AcademicProgramProjection
- CampusProjection
- TuitionProjection
- AdmissionRequirementsProjection
- IntakeCalendarProjection
- AccreditationProjection
- UniversityComparisonProjection

## Implementation Rules
1. Read Models are query-optimized
2. Read Models are read-only
3. Projections consume Domain Events
4. Projections are deterministic
5. Projections are idempotent
6. Business rules remain in the domain layer
7. Eventual consistency is accepted
8. Read Models are rebuildable
9. CQRS read/write separation is preserved
10. Domain integrity is maintained

## NOT ALLOWED
- Read Models modifying write-side state
- Projections executing business rules
- Projections modifying aggregates
- Projections publishing commands
- Read Models accessing write repositories
