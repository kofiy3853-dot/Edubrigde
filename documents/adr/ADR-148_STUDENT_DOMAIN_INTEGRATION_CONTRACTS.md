# ADR-148: Student Domain Integration Contracts

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Integration Contracts
**Decision:** Student Domain Integration Contracts

## Context

EduBridge OS requires a structured mechanism for cross-bounded-context communication. Without explicit integration contracts, external bounded contexts would access internal Student domain objects directly, violating encapsulation and creating tight coupling.

## Decision

The Student bounded context exposes Integration Contracts as its only supported mechanism for cross-bounded-context communication. External bounded contexts interact with the Student domain only through these contracts. Internal aggregates, entities, value objects, repositories, and application services remain private implementation details.

## Consequences

### Positive

- Clean separation between internal and external concerns
- Stable contracts enable independent evolution
- Anti-Corruption Layers protect domain integrity
- Versioned contracts support backward compatibility
- Domain autonomy is preserved

### Negative

- Contract proliferation requires governance
- ACL maintenance adds complexity
- Indirection between bounded contexts

## Scope

### Owned
- Student identity contracts
- Student profile contracts
- Academic summary contracts
- Student lifecycle contracts
- Student status contracts
- Student eligibility contracts (Student-owned rules only)

### NOT Owned
- Internal aggregate implementation
- Internal repository interfaces
- Internal domain services
- Internal value object implementations
- Cross-domain contract ownership

## Public Contract Catalog
- StudentIdentityContract
- StudentReferenceContract
- StudentProfileContract
- StudentContactContract
- AcademicSummaryContract
- EducationHistorySummaryContract
- StudentStatusContract
- StudentLifecycleContract
- StudentGoalSummaryContract
- StudentPreferenceSummaryContract

## Implementation Rules
1. Integration Contracts define the public boundary
2. Internal domain models remain private
3. ACLs isolate external models
4. Contracts are versioned
5. Semantic compatibility is preserved where practical
6. Breaking changes require new versions
7. Consumers never access aggregates directly
8. Governance is mandatory
9. Contracts remain technology-independent
10. Domain autonomy is preserved

## NOT ALLOWED
- External bounded contexts redefining Student-owned contracts
- Direct access to internal aggregates, repositories, or domain services
- Contract exposure of implementation details
- Bypassing Anti-Corruption Layers
- Contracts containing business logic
