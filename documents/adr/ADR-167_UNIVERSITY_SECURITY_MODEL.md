# ADR-167: University Security Model

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Security Model
**Decision:** University Security Model

## Context

EduBridge OS requires structured authorization requirements for university resources. Without a defined security model, access control would be inconsistent, unauditable, and prone to privilege escalation.

## Decision

The University bounded context defines authorization requirements for university resources while delegating authentication, identity verification, and permission enforcement to the platform-wide Security bounded context. The University domain specifies business authorization rules but does not implement authentication mechanisms.

## Consequences

### Positive

- Clean separation between authorization and authentication
- Business-oriented permissions aligned with domain responsibilities
- Least-privilege enforcement
- Comprehensive auditability
- Technology-independent security policies

### Negative

- Cross-context dependency on Security bounded context
- Permission granularity requires ongoing governance
- Authorization evaluation adds processing overhead

## Scope

### Owned
- University authorization requirements
- Business permission definitions
- Resource ownership rules
- Role boundary specifications
- Authorization policy definitions
- Audit requirement specifications

### NOT Owned
- Authentication providers
- JWT handling
- OAuth/OpenID Connect
- Session management
- Encryption technologies
- Identity verification
- Permission enforcement

## Protected Resources
- University Profiles
- Academic Programs
- Campuses
- Faculties
- Admission Requirements
- Tuition Structures
- Intake Periods
- Accreditation Records
- Contact Information
- Publication Status

## Permission Catalog
- University: View, Create, Update, Publish, Archive, Delete
- Academic Program: View, Create, Update, Archive
- Campus: View, Create, Update, Archive
- Admission: View, Update
- Tuition: View, Update
- Accreditation: View, Update
- Intake: View, Create, Update

## Implementation Rules
1. Authentication is delegated to the Security bounded context
2. The University bounded context defines authorization requirements only
3. Permissions are business-oriented
4. Resource ownership is explicit
5. Authorization follows least privilege
6. Auditability is mandatory
7. Security policies remain technology-independent
8. Business terminology is preserved
9. Authorization decisions are independently testable
10. Domain integrity is maintained

## NOT ALLOWED
- University Aggregate implementing authentication logic
- University Aggregate validating JWTs
- University Aggregate accessing identity providers
- Business logic embedding authentication mechanisms
- Direct credential validation within domain
