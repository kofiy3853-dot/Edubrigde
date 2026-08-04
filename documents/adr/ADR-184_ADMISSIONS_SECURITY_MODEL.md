# ADR-184: Admissions Security Model

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Security Model
**Decision:** Admissions Security Model

## Context

EduBridge OS requires structured authorization requirements for admissions resources. Without a defined security model, access control would be inconsistent and unauditable.

## Decision

The Admissions bounded context defines authorization requirements for admissions resources while delegating authentication to the platform-wide Security bounded context.

## Consequences

### Positive

- Clean separation between authorization and authentication
- Business-oriented permissions
- Least-privilege enforcement
- Comprehensive auditability
- Technology-independent security policies

### Negative

- Cross-context dependency on Security bounded context
- Permission granularity requires governance
- Authorization evaluation adds overhead

## Scope

### Owned
- Admission authorization requirements
- Business permission definitions
- Resource ownership rules
- Role boundary specifications

### NOT Owned
- Authentication providers
- JWT handling
- Session management
- Encryption technologies

## Implementation Rules
1. Authentication is delegated to Security context
2. Authorization requirements are defined by domain
3. Permissions are business-oriented
4. Resource ownership is explicit
5. Authorization follows least privilege
6. Auditability is mandatory
7. Security policies remain technology-independent
8. Business terminology is preserved
9. Authorization decisions are independently testable
10. Domain integrity is maintained
