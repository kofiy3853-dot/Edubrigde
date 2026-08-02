# ADR-151: Student Domain Exception Model

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Exception Model
**Decision:** Student Domain Exception Model

## Context

EduBridge OS requires a structured exception model for the Student bounded context. Without a domain exception model, business failures would be expressed inconsistently across infrastructure or embedded in business logic, reducing testability and traceability.

## Decision

The Student bounded context adopts a structured Domain Exception Model to represent business failures consistently across the Student Domain. Domain Exceptions express violations of business rules, invariants, or policies. They do not represent infrastructure failures, programming defects, or transport errors.

## Consequences

### Positive

- Consistent expression of business failures
- Clear separation between domain and infrastructure exceptions
- Traceable exception hierarchy
- Business-oriented exception messages
- Technology-independent design

### Negative

- Exception proliferation requires governance
- Exception hierarchy maintenance adds complexity
- Translation between layers adds indirection

## Scope

### Owned
- Student lifecycle exceptions
- Student profile exceptions
- Academic profile exceptions
- Student goal exceptions
- Learning preference exceptions
- Aggregate consistency exceptions
- Student policy exceptions

### NOT Owned
- Infrastructure exceptions (database, network, transport)
- Programming defects
- HTTP status codes
- Logging frameworks
- Monitoring systems

## Exception Hierarchy
- StudentDomainException
  - ValidationException
  - AggregateInvariantViolationException
  - StudentLifecycleException
  - StudentProfileException
  - AcademicProfileException
  - StudentGoalException
  - StudentPreferenceException
  - AuthorizationException
  - BusinessPolicyException

## Implementation Rules
1. Exceptions represent business failures only
2. Infrastructure failures remain outside the domain
3. Exception hierarchy follows business concepts
4. Business messages use ubiquitous language
5. Exception translation occurs outside the domain
6. Aggregate invariants remain protected
7. Domain Exceptions are technology-independent
8. Internal implementation details are never exposed
9. Exceptions are independently testable
10. Governance is mandatory

## NOT ALLOWED
- Database errors in Domain Exceptions
- HTTP status codes in Domain Exceptions
- Logging framework dependencies in Domain Exceptions
- Infrastructure coupling in Domain Exceptions
- Internal implementation details in exception messages
