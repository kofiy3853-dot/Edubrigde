# ADR-155: University Entities

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Entities
**Decision:** University Entities

## Context

EduBridge OS requires identity-bearing business objects within the University bounded context. Without defined entities, the University Aggregate would lack the internal structure needed to represent institutional information.

## Decision

The University Aggregate owns a collection of Entities that represent identity-bearing business objects within the University bounded context. Each entity has a persistent identity, lifecycle, and business responsibilities, but may only be created, modified, or removed through the University Aggregate Root.

## Consequences

### Positive

- Clear entity ownership by the Aggregate
- Stable identities for business objects
- Local invariant enforcement
- Consistent lifecycle management
- Protected entity boundaries

### Negative

- Entity proliferation requires governance
- Cross-entity coordination adds complexity
- Aggregate consistency maintenance overhead

## Scope

### Owned
- Campus
- Faculty
- AcademicProgram
- AdmissionRequirement
- TuitionStructure
- IntakePeriod
- AccreditationRecord
- ContactDirectory

### NOT Owned
- Student entities (Student bounded context)
- Scholarship entities (Financial bounded context)
- Notification entities (Communication bounded context)

## Entity Catalog
- Campus (CampusId)
- Faculty (FacultyId)
- AcademicProgram (ProgramId)
- AdmissionRequirement (AdmissionRequirementId)
- TuitionStructure (TuitionStructureId)
- IntakePeriod (IntakePeriodId)
- AccreditationRecord (AccreditationRecordId)
- ContactDirectory (ContactDirectoryId)

## Implementation Rules
1. Every entity has a stable identity
2. All entities are owned by the University Aggregate
3. Entities enforce local business invariants
4. Aggregate Root coordinates entity interactions
5. Entity identity is immutable
6. Cross-domain operations are prohibited
7. Repository access from entities is prohibited
8. Domain events are published by the Aggregate Root only
9. Infrastructure dependencies are prohibited
10. Aggregate consistency is preserved

## NOT ALLOWED
- Direct modification of entities by external consumers
- Cross-domain operations on entities
- Repository access from entities
- Independent domain event publication by entities
- Infrastructure dependencies in entities
