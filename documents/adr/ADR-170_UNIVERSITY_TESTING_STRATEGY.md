# ADR-170: University Testing Strategy

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Testing Strategy
**Decision:** University Testing Strategy

## Context

EduBridge OS requires a structured testing architecture for the University bounded context. Without a layered testing strategy, test coverage would be inconsistent, business correctness would be unverified, and architectural integrity would be unvalidated.

## Decision

The University bounded context adopts a layered testing strategy that verifies business correctness, architectural integrity, and integration behavior independently. Testing focuses on validating business outcomes rather than implementation details.

## Consequences

### Positive

- Business behavior validated through deterministic tests
- CQRS read/write sides tested independently
- Repository contract testing ensures implementation compliance
- Projection testing guarantees replayability and idempotency
- Coverage standards establish clear quality benchmarks

### Negative

- Test maintenance requires ongoing governance
- Mocking restrictions increase test setup complexity
- Contract testing adds operational overhead

## Scope

### Owned
- Unit testing strategy
- Aggregate testing
- Domain service testing
- Contract testing
- CQRS testing
- Event testing
- Projection testing
- Coverage standards

### NOT Owned
- CI/CD pipelines
- Deployment testing
- Browser automation tools
- Infrastructure-specific testing frameworks

## Testing Pyramid
- Entity & Value Object Unit Tests (base)
- Aggregate & Domain Service Tests
- Application Service Tests
- Integration Tests
- End-to-End Tests (top)

## Implementation Rules
1. Tests verify business behavior
2. Domain logic is tested directly
3. CQRS layers are tested independently
4. Projections are replayable and idempotent
5. Repository implementations satisfy contract tests
6. Integration contracts remain backward compatible
7. Mocks are limited to external dependencies
8. Tests remain deterministic
9. Business terminology is preserved
10. Architectural integrity is continuously verified

## NOT ALLOWED
- Tests verifying private internal state
- Tests depending on database implementation details
- Tests depending on framework internals
- Shared mutable fixtures
- Mocking aggregate behavior, domain policies, value objects, or core business rules
