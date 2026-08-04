# ADR-173: Admissions Value Objects

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Admissions Value Objects
**Decision:** Admissions Value Object Catalog

## Context

EduBridge OS requires structured value object definitions for the Admission Aggregate. Without defined value objects, the admissions workflow would lack immutable business concepts, self-validation, and value-based equality.

## Decision

The Admissions bounded context adopts Value Objects to represent immutable business concepts that do not require identity. Value Objects encapsulate validation, business semantics, and equality while ensuring consistency across the Admission Aggregate.

## Consequences

### Positive

- Immutable value objects ensure consistency
- Self-validation prevents invalid state
- Value-based equality simplifies comparisons
- Business semantics encapsulated
- Primitive obsession avoided

### Negative

- Value object proliferation increases catalog complexity
- Immutability requires object replacement for changes
- Validation during construction adds overhead

## Scope

### Owned
- AdmissionStatus
- DecisionType
- EligibilityResult
- OfferStatus
- WaitlistPosition
- ReviewScore
- AdmissionCycle
- ApplicationReference
- ReviewComment
- DecisionReason
- OfferExpiry
- AdmissionPriority

### NOT Owned
- Entities
- Repositories
- Persistence
- Infrastructure

## Value Object Catalog
- AdmissionStatus: Current lifecycle state of admission
- DecisionType: Outcome of admission decision
- EligibilityResult: Outcome of eligibility evaluation
- OfferStatus: Current state of admission offer
- WaitlistPosition: Applicant position on waitlist
- ReviewScore: Score assigned during review
- AdmissionCycle: Academic admission cycle
- ApplicationReference: Human-readable application reference
- ReviewComment: Reviewer observations
- DecisionReason: Official reason supporting decision
- OfferExpiry: Deadline for accepting offer
- AdmissionPriority: Processing priority

## Implementation Rules
1. Value Objects are immutable
2. Equality is value-based
3. Validation occurs during construction
4. Identity is prohibited
5. Business terminology is preserved
6. Primitive obsession is avoided
7. Infrastructure dependencies are prohibited
8. Value Objects are independently testable
9. Aggregate consistency is supported
10. Domain integrity is maintained

## NOT ALLOWED
- Value Object access to repositories
- Value Object access to databases
- Value Object access to HTTP requests
- Value Object access to presentation layer
- Value Object mutation after creation
- Value Object identity
