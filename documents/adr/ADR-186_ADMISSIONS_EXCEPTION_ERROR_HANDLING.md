# ADR-186: Admissions Exception & Error Handling Model

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Exception & Error Handling Model
**Decision:** Admissions Exception & Error Handling Model

## Context

EduBridge OS requires a structured exception and error handling architecture for the Admissions bounded context. Without a layered exception model, error handling would be inconsistent and unauditable.

## Decision

The Admissions bounded context adopts a layered exception model where each architectural layer owns its own exception types and responsibilities.

## Consequences

### Positive

- Clear separation of exception responsibilities
- Business-oriented exceptions preserve domain language
- Immutable exceptions ensure consistency
- Technology-independent error models
- Traceable exceptions support debugging

### Negative

- Exception proliferation requires governance
- Layer coordination for translation adds complexity
- Exception catalog maintenance requires management

## Scope

### Owned
- Validation exceptions
- Domain exceptions
- Application exceptions
- Integration exceptions
- Error propagation rules

### NOT Owned
- HTTP status codes
- REST error payloads
- UI notifications
- Logging frameworks

## Implementation Rules
1. Exceptions are layered
2. Domain exceptions originate from domain
3. Application exceptions coordinate orchestration
4. Exceptions are immutable
5. Business terminology is preserved
6. Infrastructure details are never exposed
7. Exception translation occurs outside domain
8. Exceptions are independently testable
9. Recovery policies remain outside domain
10. Domain integrity is preserved
