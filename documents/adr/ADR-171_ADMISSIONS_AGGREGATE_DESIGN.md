# ADR-171: Admissions Aggregate Design

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Admissions Aggregate Design
**Decision:** Admission as Aggregate Root

## Context

EduBridge OS requires a structured aggregate design for the Admissions bounded context. Without a defined aggregate root, the admissions workflow would lack transactional consistency, business invariant enforcement, and lifecycle management.

## Decision

The Admissions bounded context adopts Admission as its Aggregate Root. The Admission Aggregate owns the complete lifecycle of an admission application from submission through eligibility evaluation, review, decision, offer management, and enrollment readiness. The Aggregate is the only consistency boundary within the Admissions domain.

## Consequences

### Positive

- Clear transactional consistency boundary
- Complete admission lifecycle ownership
- Enforced business invariants
- Controlled state transitions
- Domain Event generation from aggregate

### Negative

- Aggregate complexity increases with lifecycle management
- Cross-aggregate consistency requires eventual consistency
- Aggregate boundary limits parallelism

## Scope

### Owned
- Admission application lifecycle
- Application status management
- Eligibility evaluation coordination
- Review workflow management
- Admission decision recording
- Offer issuance and management
- Waitlist transitions
- Enrollment readiness preparation

### NOT Owned
- Entity definitions
- Value object definitions
- Repository implementations
- Persistence configurations
- API definitions

## Aggregate Root
- Admission (Aggregate Root)

## Aggregate Boundary
- Admission
- Applicant Reference
- University Reference
- Academic Program Reference
- Admission Cycle
- Eligibility Result
- Admission Reviews
- Decision
- Offer
- Waitlist Entry
- Admission Timeline

## Implementation Rules
1. Admission is the Aggregate Root
2. All modifications pass through the Aggregate
3. Business invariants are always enforced
4. Lifecycle transitions are controlled
5. Aggregate boundaries remain explicit
6. External systems communicate through Integration Contracts
7. Infrastructure dependencies are prohibited
8. Domain Events originate from the Aggregate
9. Business terminology is preserved
10. Domain integrity is maintained

## NOT ALLOWED
- Direct database access from aggregate
- REST API dependencies in aggregate
- UI dependencies in aggregate
- External authentication dependencies in aggregate
- Child object modification outside aggregate
