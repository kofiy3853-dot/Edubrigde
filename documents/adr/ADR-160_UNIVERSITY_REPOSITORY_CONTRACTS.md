# ADR-160: University Repository Contracts

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Repository Contracts
**Decision:** University Repository Contracts

## Context

EduBridge OS requires a persistence abstraction for the University Aggregate. Without Repository Contracts, persistence logic would be embedded in aggregates or infrastructure, reducing testability and separation of concerns.

## Decision

The University bounded context adopts Repository Contracts to abstract persistence operations for the University Aggregate. Repositories provide the only persistence gateway for Aggregate Roots. Repository interfaces belong to the Domain Layer, while implementations belong to the Infrastructure Layer.

## Consequences

### Positive

- Clear separation between domain and persistence
- Technology-independent interfaces
- Aggregate-oriented persistence
- Optimistic concurrency support
- Specification-based retrieval

### Negative

- Repository proliferation requires governance
- Concurrency management adds complexity
- Specification composition requires careful design

## Scope

### Owned
- UniversityRepository interface
- Aggregate persistence contracts
- Query capabilities
- Concurrency expectations
- Identity generation

### NOT Owned
- Database implementations
- ORM mappings
- Infrastructure technologies
- Child entity repositories

## Repository Contract
- save(University)
- findById(UniversityId)
- exists(UniversityId)
- findBySpecification(Specification)
- delete(University)
- nextIdentity()

## Implementation Rules
1. Repositories persist Aggregate Roots only
2. Child entities never have independent repositories
3. Business logic is prohibited inside repositories
4. Repository interfaces remain technology-independent
5. Optimistic concurrency is mandatory
6. Aggregate consistency is preserved
7. Specifications support complex retrieval
8. Identity generation is abstracted
9. Repository implementations belong to Infrastructure
10. Domain integrity is maintained

## NOT ALLOWED
- Child entity repositories
- Business logic in repositories
- Infrastructure dependencies in interfaces
- Arbitrary infrastructure queries
- Direct database access from aggregates
