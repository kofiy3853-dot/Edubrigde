# ADR-168: University Validation Framework

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Validation Framework
**Decision:** University Validation Framework

## Context

EduBridge OS requires a structured validation architecture for the University bounded context. Without a layered validation framework, validation responsibilities would be scattered, leading to business rule duplication and inconsistent enforcement.

## Decision

The University bounded context adopts a layered validation framework that separates structural validation, application validation, domain validation, and policy validation. Validation responsibilities are assigned to the appropriate architectural layer to preserve domain integrity and maintain a clean separation of concerns.

## Consequences

### Positive

- Clear separation of validation responsibilities by layer
- Business invariants protected at the aggregate level
- Organizational policies externalized and testable
- Immutable validation errors ensure consistency
- Technology-independent validation logic

### Negative

- Layer coordination requires governance
- Validation proliferation requires catalog management
- Indirection between validation layers adds complexity

## Scope

### Owned
- Structural validation rules
- Application validation rules
- Domain validation rules
- Policy validation rules
- Validation error models
- Validation governance

### NOT Owned
- UI validation libraries
- HTTP request validation frameworks
- Database constraints
- Presentation-layer validation

## Validation Layers
1. Structural Validation: Verify command and query structure
2. Application Validation: Verify application-level conditions
3. Domain Validation: Protect business invariants
4. Policy Validation: Evaluate organizational and business policies

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

## NOT ALLOWED
- UI owning business validation logic
- Database owning business rule ownership
- Infrastructure owning domain validation
- Business rule duplication across layers
- Validation logic embedded in presentation layer
