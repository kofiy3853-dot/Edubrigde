# ADR-154: University Aggregate Root

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Aggregate
**Decision:** University Aggregate Root

## Context

EduBridge OS requires a consistency boundary for university-owned information. Without a defined Aggregate, modifications to university data would lack transactional consistency and business invariant enforcement.

## Decision

The University bounded context adopts a single Aggregate Root named University. The University Aggregate is the authoritative business consistency boundary for all university-owned information. All modifications to university data must occur through the Aggregate Root to preserve business invariants and transactional consistency.

## Consequences

### Positive

- Clear consistency boundary for university data
- Business invariants are enforced
- Transactional consistency is preserved
- Child entities are protected
- Version consistency is maintained

### Negative

- Aggregate proliferation requires governance
- Child entity management adds complexity
- Aggregate boundaries require careful design

## Scope

### Owned
- University profile
- Campuses
- Academic programs
- Faculties
- Admission requirements
- Tuition structures
- Accreditation information
- Rankings (business representation)
- Intake periods
- Contact information

### NOT Owned
- Student profiles
- Student applications
- Scholarships
- Financial aid
- Authentication
- Notifications

## Aggregate Invariants
- Unique institutional identity
- At least one official name
- Valid institutional status
- Consistent program ownership
- Valid campus ownership
- Valid admission requirement associations
- Consistent tuition ownership
- Version consistency

## Implementation Rules
1. The University Aggregate is the only Aggregate Root
2. All state changes occur through the Aggregate Root
3. Aggregate invariants are mandatory
4. Child entities are owned exclusively by the aggregate
5. Identity is immutable
6. Aggregate versioning is required
7. Business rules remain within the domain
8. Cross-domain modifications are prohibited
9. Infrastructure dependencies are prohibited
10. Aggregate consistency is preserved at all times

## NOT ALLOWED
- Direct modification of child entities by external consumers
- Cross-domain modifications
- Infrastructure dependencies
- Operations violating invariants
- Bypassing the Aggregate Root
