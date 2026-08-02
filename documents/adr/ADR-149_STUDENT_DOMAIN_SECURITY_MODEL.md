# ADR-149: Student Domain Security Model

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Security Model
**Decision:** Student Domain Security Model

## Context

EduBridge OS requires a structured authorization model for the Student bounded context. Without a domain-level security model, authorization decisions would be scattered across infrastructure or embedded in business logic, reducing testability and consistency.

## Decision

The Student bounded context adopts a Domain Security Model that governs business authorization and access control independently of authentication infrastructure. Authentication establishes who the caller is. The Student Domain determines what the caller is permitted to do.

## Consequences

### Positive

- Clear separation between authentication and authorization
- Business-owned authorization ensures consistency
- Least-privilege principles are enforced
- Auditability supports compliance
- Technology-independent design enables flexibility

### Negative

- Security policy proliferation requires governance
- Permission management adds complexity
- Domain-level authorization adds indirection

## Scope

### Owned
- Student profile management authorization
- Student goal management authorization
- Academic profile management authorization
- Student preference authorization
- Student lifecycle action authorization
- Student-owned business resource authorization

### NOT Owned
- Authentication infrastructure (owned by Identity & Access bounded context)
- JWT validation
- OAuth/OpenID Connect
- Network security

## Identity Boundaries
- Student
- Parent/Guardian
- Academic Advisor
- University Staff
- Admissions Officer
- Scholarship Officer
- System Administrator

## Permission Model
- ViewStudentProfile
- UpdateStudentProfile
- UpdateStudentContact
- ViewAcademicProfile
- UpdateAcademicProfile
- AddEducationRecord
- RecordAcademicMilestone
- CreateStudentGoal
- UpdateStudentGoal
- CompleteStudentGoal
- ArchiveStudentGoal
- ViewPreferences
- UpdatePreferences
- ActivateStudent
- SuspendStudent
- ArchiveStudent
- RestoreStudent

## Implementation Rules
1. Authentication and authorization remain separate concerns
2. Authorization is business-owned
3. Permissions are explicit
4. Resource ownership is defined
5. Authorization precedes business execution
6. Least-privilege principles are mandatory
7. Auditability is preserved
8. Security policies remain technology-independent
9. Domain autonomy is maintained
10. Cross-domain authorization logic is prohibited

## NOT ALLOWED
- Authentication infrastructure in the Student Domain
- JWT validation in the Student Domain
- OAuth/OpenID Connect in the Student Domain
- Network security in the Student Domain
- Cross-domain authorization logic
- Business logic in infrastructure security
