# ADR-156: University Value Objects

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Value Objects
**Decision:** University Value Objects

## Context

EduBridge OS requires immutable business concepts within the University bounded context. Without Value Objects, the University Aggregate would lack the descriptive, identity-free components needed to represent business values.

## Decision

The University bounded context adopts Value Objects to model immutable business concepts. Every Value Object represents a descriptive business value without identity. Equality is determined entirely by the values contained within the object rather than by object identity.

## Consequences

### Positive

- Immutable values ensure correctness
- Self-validating objects enforce business rules
- Value-based equality supports consistency
- Reusable components reduce duplication
- Technology-independent design enables flexibility

### Negative

- Value Object proliferation requires governance
- Immutability requires new instances for changes
- Composition adds complexity

## Scope

### Owned
- University identity attributes
- Institutional information
- Academic programs
- Admission requirements
- Tuition
- Accreditation
- Contact information
- Campus information

### NOT Owned
- Student value objects (Student bounded context)
- Financial value objects (Financial bounded context)
- Cross-domain value objects

## Value Object Catalog
- UniversityName
- UniversityCode
- CampusAddress
- ContactInformation
- TuitionAmount
- AcademicProgramName
- ProgramDuration
- AdmissionCriteria
- AccreditationStatus
- IntakeWindow
- RankingScore

## Implementation Rules
1. Value Objects have no identity
2. Equality is value-based
3. Immutability is mandatory
4. Validation occurs during construction
5. Infrastructure dependencies are prohibited
6. Value Objects may be composed
7. Business terminology is preserved
8. Mutable state is prohibited
9. Reuse is encouraged
10. Domain integrity is maintained

## NOT ALLOWED
- Mutable state in Value Objects
- Identity in Value Objects
- Infrastructure dependencies in Value Objects
- Repository access from Value Objects
- Database access from Value Objects
