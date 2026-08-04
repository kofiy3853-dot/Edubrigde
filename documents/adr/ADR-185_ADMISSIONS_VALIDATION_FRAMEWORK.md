# ADR-185: Admissions Validation Framework

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Validation Framework
**Decision:** Admissions Validation Framework

## Context

EduBridge OS requires a structured validation architecture for the Admissions bounded context. Without a layered validation framework, validation responsibilities would be scattered and inconsistent.

## Decision

The Admissions bounded context adopts a layered validation framework that separates structural, application, domain, and policy validation.

## Consequences

### Positive

- Clear separation of validation responsibilities
- Business invariants protected at aggregate level
- Organizational policies externalized and testable
- Immutable validation errors ensure consistency
- Technology-independent validation logic

### Negative

- Layer coordination requires governance
- Validation proliferation requires catalog management
- Indirection between layers adds complexity

## Scope

### Owned
- Structural validation rules
- Application validation rules
- Domain validation rules
- Policy validation rules
- Validation error models

### NOT Owned
- UI validation libraries
- HTTP request validation frameworks
- Database constraints

## Implementation Rules
1. Validation is layered
2. Structural validation occurs first
3. Business invariants belong to Aggregates
4. Organizational rules belong to Policies
5. Validation errors are immutable
6. Validation logic is technology-independent
7. Rules are independently testable
8. Business rule duplication is prohibited
9. Validation uses ubiquitous language
10. Domain integrity is preserved
