# ADR-152: Student Domain Testing Strategy

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Testing Strategy
**Decision:** Student Domain Testing Strategy

## Context

EduBridge OS requires a structured testing approach for the Student bounded context. Without a layered testing strategy, verification would be inconsistent, reducing confidence in business correctness and long-term maintainability.

## Decision

The Student bounded context adopts a layered testing strategy that validates each architectural component independently while ensuring end-to-end business correctness. Testing focuses on business behavior rather than implementation details.

## Consequences

### Positive

- Consistent verification across all components
- Business-focused tests ensure correctness
- Layered approach enables fast feedback
- Isolated testing reduces dependencies
- Deterministic tests improve reliability

### Negative

- Test proliferation requires governance
- Multiple test layers add maintenance overhead
- Contract testing adds complexity

## Scope

### Owned
- Value object testing
- Entity testing
- Aggregate testing
- Domain service testing
- Policy testing
- Command testing
- Query testing
- Projection testing
- Repository contract testing
- Integration contract testing

### NOT Owned
- CI/CD pipeline configuration
- Load testing
- Penetration testing
- Infrastructure monitoring
- E2E infrastructure testing

## Testing Pyramid
```
            End-to-End
                ^
         Integration Tests
                ^
     Application Service Tests
                ^
   Aggregate & Domain Service Tests
                ^
 Value Object & Unit Tests
```

## Implementation Rules
1. Tests verify business behavior
2. Tests are deterministic
3. Aggregates are tested without infrastructure
4. Domain Services are tested directly
5. Repository implementations share contract tests
6. CQRS read and write models are tested separately
7. Domain events are verified explicitly
8. Shared mutable test fixtures are prohibited
9. Test names use ubiquitous language
10. Business correctness takes precedence over implementation details

## NOT ALLOWED
- Unit tests depending on production databases
- Aggregate tests depending on external APIs
- Value object tests depending on infrastructure services
- Shared mutable test fixtures
- Tests verifying implementation details over business behavior
