# ADR-180: Admissions Commands & Command Handlers

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Commands & Command Handlers
**Decision:** Admissions Command & Handler Catalog

## Context

EduBridge OS requires structured commands and command handlers for the Admissions bounded context. Without defined commands, write-side operations would lack standardization, validation, and clear execution paths.

## Decision

The Admissions bounded context adopts Commands and Command Handlers as the write-side implementation of CQRS. Commands represent business intentions; handlers execute write operations against the Admission Aggregate.

## Consequences

### Positive

- Clear CQRS write-side separation
- Commands capture business intent
- Handlers encapsulate write logic
- Commands are independently testable
- Write operations are traceable

### Negative

- Command proliferation requires governance
- Handler complexity increases with aggregate size
- Command validation adds overhead

## Scope

### Owned
- Application commands
- Eligibility commands
- Review commands
- Decision commands
- Offer commands
- Waitlist commands
- Enrollment commands

### NOT Owned
- Query operations
- Read model operations
- External API definitions

## Implementation Rules
1. Commands represent business intentions
2. Commands are immutable
3. One command maps to one handler
4. Command handlers are stateless
5. Command handlers execute against aggregates
6. Infrastructure dependencies are prohibited at domain level
7. Business terminology is preserved
8. Commands are independently testable
9. Aggregate consistency is maintained
10. Domain integrity is preserved
