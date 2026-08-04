# ADR-172: Admissions Entities

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Admissions Entities
**Decision:** Admission Entity Catalog

## Context

EduBridge OS requires structured entity definitions for the Admission Aggregate. Without defined entities, the admissions workflow would lack identity-bearing business concepts, lifecycle management, and consistent state transitions.

## Decision

The Admission Aggregate contains multiple Entities representing business concepts that require identity and lifecycle management. Entities are managed exclusively by the Admission Aggregate and cannot exist independently.

## Consequences

### Positive

- Clear entity responsibilities within aggregate
- Identity-based equality ensures consistency
- Lifecycle management controlled by aggregate
- Business behavior encapsulated within entities
- Auditability through timeline entries

### Negative

- Entity proliferation increases aggregate complexity
- Lifecycle management adds governance overhead
- Entity relationships require careful coordination

## Scope

### Owned
- AdmissionReview
- AdmissionDecision
- AdmissionOffer
- WaitlistEntry
- EligibilityAssessment
- AdmissionTimelineEntry

### NOT Owned
- Value objects
- Repositories
- Persistence
- Infrastructure

## Entity Catalog
- AdmissionReview: Academic/administrative review of application
- AdmissionDecision: Official admission outcome
- AdmissionOffer: Offer issued to applicant
- WaitlistEntry: Applicant on waitlist
- EligibilityAssessment: Eligibility evaluation
- AdmissionTimelineEntry: Auditable milestone

## Implementation Rules
1. Entities possess immutable identities
2. Entities exist only within the Admission Aggregate
3. The Aggregate controls all entity lifecycle operations
4. Business behavior resides within entities
5. Infrastructure dependencies are prohibited
6. Entity equality is identity-based
7. Business terminology is preserved
8. Timeline entries are immutable
9. Auditability is maintained
10. Domain integrity is preserved

## NOT ALLOWED
- Entity access to repositories
- Entity access to databases
- Entity access to REST APIs
- Entity access to presentation layer
- Entity existence outside aggregate
- Entity sharing across aggregates
