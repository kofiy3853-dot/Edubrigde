# ADR-182: Admissions Read Models & Projections

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Read Models & Projections
**Decision:** Admissions Read Model & Projection Catalog

## Context

EduBridge OS requires structured read models and projections for the Admissions bounded context. Without defined read models, query operations would lack optimized representations and efficient data access.

## Decision

The Admissions bounded context adopts Read Models and Projections as the read-side implementation of CQRS. Read Models provide query-optimized representations; Projections consume Domain Events to maintain these models.

## Consequences

### Positive

- Query-optimized data representations
- Event-driven projections ensure consistency
- Independent read/write sides improve scalability
- Deterministic projections enable rebuildability
- Idempotent projections support fault tolerance

### Negative

- Read model proliferation requires governance
- Projection maintenance adds operational complexity
- Eventual consistency requires careful management

## Scope

### Owned
- Application summary read models
- Status read models
- Eligibility read models
- Review read models
- Decision read models
- Offer read models
- Waitlist read models

### NOT Owned
- Write-side aggregates
- Write-side repositories
- Domain event publishing

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
