# ADR-161: University Repository Specifications

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Repository Specifications
**Decision:** University Repository Specifications

## Context

EduBridge OS requires reusable business query criteria within the University bounded context. Without the Specification Pattern, business filtering logic would be embedded in repositories or infrastructure, reducing reusability and testability.

## Decision

The University bounded context adopts the Specification Pattern for expressing reusable business query criteria. Specifications encapsulate business filtering logic independently of repository implementations and persistence technologies.

## Consequences

### Positive

- Reusable business filtering logic
- Composable specifications
- Technology-independent design
- Testable business criteria
- Clean separation from persistence

### Negative

- Specification proliferation requires governance
- Composition complexity increases
- Specification boundaries require careful design

## Scope

### Owned
- University publication specifications
- Accreditation specifications
- Academic program specifications
- Campus specifications
- Admission requirement specifications
- Tuition structure specifications
- Intake period specifications
- Institutional lifecycle specifications

### NOT Owned
- SQL queries
- ORM expressions
- Database indexes
- Database optimizations

## Specification Catalog
- PublishedUniversitySpecification
- VerifiedUniversitySpecification
- AccreditedUniversitySpecification
- ActiveCampusSpecification
- ProgramAvailabilitySpecification
- DegreeLevelSpecification
- TuitionRangeSpecification
- IntakeAvailabilitySpecification
- CountrySpecification
- RankingThresholdSpecification

## Implementation Rules
1. Specifications are immutable
2. Specifications encapsulate business filtering logic
3. Repository implementations evaluate specifications
4. Infrastructure dependencies are prohibited
5. Specifications are reusable
6. Composition is supported
7. Business terminology is preserved
8. Specifications are independently testable
9. Duplicate specifications are prohibited
10. Domain integrity is maintained

## NOT ALLOWED
- Specifications depending on database
- Specifications depending on HTTP requests
- Specifications depending on presentation layer
- Specifications depending on messaging infrastructure
- Duplicate business logic in specifications
