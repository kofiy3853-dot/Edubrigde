# ADR-175: Admissions Domain Policies

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Domain Policies
**Decision:** Admissions Domain Policy Catalog

## Context

EduBridge OS requires structured domain policies for the Admissions bounded context. Without defined domain policies, business rules would be scattered, unauditable, and inconsistent across the admission lifecycle.

## Decision

The Admissions bounded context adopts Domain Policies to encapsulate business rules that govern admission eligibility, decision making, offer management, and waitlist operations. Policies remain external to entities but within the domain.

## Consequences

### Positive

- Centralized business rule management
- Policies are independently testable
- Deterministic outcomes for business decisions
- Clear separation from entity behavior
- Technology-independent policy definitions

### Negative

- Policy proliferation requires governance
- Policy coordination adds complexity
- Policy ordering may affect outcomes

## Scope

### Owned
- Admission eligibility policies
- Decision policies
- Offer policies
- Waitlist policies
- Enrollment policies

### NOT Owned
- Entity behavior
- Application orchestration
- Infrastructure concerns

## Implementation Rules
1. Policies encapsulate business rules
2. Policies are deterministic
3. Policies are independently testable
4. Policies remain within the domain layer
5. Policies do not own state
6. Business terminology is preserved
7. Infrastructure dependencies are prohibited
8. Policy outcomes are auditable
9. Aggregate consistency is maintained
10. Domain integrity is preserved
