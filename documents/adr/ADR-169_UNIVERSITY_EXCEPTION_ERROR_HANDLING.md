# ADR-169: University Exception & Error Handling Model

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Exception & Error Handling Model
**Decision:** University Exception & Error Handling Model

## Context

EduBridge OS requires a structured exception and error handling architecture for the University bounded context. Without a layered exception model, error handling would be inconsistent, unauditable, and prone to business rule leakage across architectural boundaries.

## Decision

The University bounded context adopts a layered exception model in which each architectural layer owns its own exception types and responsibilities. Exceptions communicate exceptional business conditions and execution failures. They are never used to implement normal business flow.

## Consequences

### Positive

- Clear separation of exception responsibilities by layer
- Business-oriented exceptions preserve domain language
- Immutable exceptions ensure consistency
- Technology-independent error models enable flexibility
- Traceable exceptions support debugging and auditing

### Negative

- Exception proliferation requires governance
- Layer coordination for exception translation adds complexity
- Exception catalog maintenance requires ongoing management

## Scope

### Owned
- Validation exceptions
- Domain exceptions
- Application exceptions
- Integration exceptions
- Error propagation rules
- Exception governance

### NOT Owned
- HTTP status codes
- REST error payloads
- UI notifications
- Logging frameworks
- Monitoring systems

## Exception Hierarchy
- UniversityException (base)
  - ValidationException
  - DomainException
  - ApplicationException
  - IntegrationException

## Implementation Rules
1. Exceptions are layered
2. Domain exceptions originate from the domain
3. Application exceptions coordinate orchestration failures
4. Exceptions are immutable
5. Business terminology is preserved
6. Infrastructure details are never exposed
7. Exception translation occurs outside the domain
8. Exceptions are independently testable
9. Recovery policies remain outside the domain
10. Domain integrity is preserved

## NOT ALLOWED
- Domain exceptions translating to HTTP responses
- Domain exceptions exposing database errors
- Aggregates raising presentation exceptions
- Exceptions used for normal business flow
- Infrastructure details leaking through exceptions
