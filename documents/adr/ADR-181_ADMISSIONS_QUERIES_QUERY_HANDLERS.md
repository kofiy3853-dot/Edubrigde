# ADR-181: Admissions Queries & Query Handlers

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Queries & Query Handlers
**Decision:** Admissions Query & Handler Catalog

## Context

EduBridge OS requires structured queries and query handlers for the Admissions bounded context. Without defined queries, read-side operations would lack standardization, optimization, and clear execution paths.

## Decision

The Admissions bounded context adopts Queries and Query Handlers as the read-side implementation of CQRS. Queries retrieve information without modifying state; handlers execute read operations against optimized read models.

## Consequences

### Positive

- Clear CQRS read-side separation
- Queries are immutable
- Handlers optimize read operations
- Queries are independently testable
- Read operations are traceable

### Negative

- Query proliferation requires governance
- Read model maintenance adds complexity
- Query optimization requires ongoing management

## Scope

### Owned
- Application queries
- Status queries
- Eligibility queries
- Review queries
- Decision queries
- Offer queries
- Waitlist queries

### NOT Owned
- Write operations
- Aggregate modifications
- External API definitions

## Implementation Rules
1. Queries retrieve information
2. Queries are immutable
3. One query maps to one handler
4. Query handlers are stateless
5. Query handlers access read models
6. Infrastructure dependencies are allowed at this layer
7. Business terminology is preserved
8. Queries are independently testable
9. Read/write separation is maintained
10. Domain integrity is preserved
