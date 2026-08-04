# ADR-179: Admissions Application Services

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Application Services
**Decision:** Admissions Application Service Catalog

## Context

EduBridge OS requires structured application services for the Admissions bounded context. Without defined application services, use case orchestration would lack coordination, transaction management, and clear entry points.

## Decision

The Admissions bounded context adopts Application Services to orchestrate use cases, coordinate domain objects, and manage transactions. Application services serve as the entry point for external interactions.

## Consequences

### Positive

- Clear use case orchestration
- Transaction management at application level
- Coordination of domain objects
- External API integration point
- Clear separation from domain logic

### Negative

- Application service proliferation requires governance
- Transaction scope management adds complexity
- Orchestration logic may become complex

## Scope

### Owned
- Application submission services
- Eligibility evaluation services
- Review coordination services
- Decision management services
- Offer management services
- Waitlist services
- Enrollment readiness services

### NOT Owned
- Domain logic
- Persistence implementations
- External API definitions

## Implementation Rules
1. Application services orchestrate use cases
2. Application services manage transactions
3. Application services coordinate domain objects
4. Application services do not contain business logic
5. Infrastructure dependencies are allowed at this layer
6. Business terminology is preserved
7. Application services are independently testable
8. Aggregate consistency is maintained
9. Domain integrity is preserved
10. CQRS read/write separation is respected
