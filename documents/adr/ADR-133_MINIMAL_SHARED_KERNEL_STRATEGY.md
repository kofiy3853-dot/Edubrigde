# ADR-133: Minimal Shared Kernel Strategy

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Shared Kernel Foundations
**Decision:** Minimal Shared Kernel Strategy

## Context

EduBridge OS requires a mechanism for sharing stable, domain-independent concepts across bounded contexts without introducing coupling. Without a Shared Kernel, duplicate identifiers, value objects, and enumerations would proliferate across contexts, causing inconsistency.

## Decision

EduBridge OS adopts a Minimal Shared Kernel strategy. The Shared Kernel contains only stable, domain-independent concepts that are required by two or more bounded contexts. Business rules, workflows, and domain-specific behavior MUST remain inside their owning bounded context.

## Consequences

### Positive

- Consistent identifiers and value objects across all contexts
- Reduced duplication of domain-independent concepts
- Stable foundation for cross-context communication
- Clear separation between shared primitives and business logic
- Versioned shared components enable controlled evolution

### Negative

- Shared Kernel changes require coordination across contexts
- Minimal scope may require duplication for context-specific variants
- Stability requirement may slow adoption of new shared concepts

## Scope

### Included in Shared Kernel
- Global Identifiers
- Value Objects
- Common Enumerations
- Primitive Types
- Shared Exceptions
- Domain Event Base Types
- Common Validation Contracts

### Excluded from Shared Kernel
- Student Rules
- University Rules
- Admission Logic
- Scholarship Logic
- Financial Logic
- Learning Logic
- Business Services
- Business Policies

## Dependency Rules

### Allowed
```
Student
        \
University ---> Shared Kernel
        /
Scholarship
```

### Not Allowed
```
Student
     |
University
     |
Financial
```

Business domains communicate through contracts and events — not by directly depending on each other.

## Shared Kernel Principles

Every shared component MUST be:
- Stable
- Domain-independent
- Immutable (where applicable)
- Versioned
- Well documented
- Backward compatible where practical
- Independently testable

## Evolution Rules

A component may enter the Shared Kernel only if:
- It is required by multiple bounded contexts
- It contains no business rules
- It has stable semantics
- Ownership is clearly defined
- Backward compatibility can be maintained

Otherwise, it remains in its original bounded context.

## NOT ALLOWED

- Business logic in the Shared Kernel
- Domain service implementations
- Aggregate roots
- Repositories
- Workflows
- Business policies
- Cross-domain coupling
- Unversioned shared components
- Mutable shared value objects
- Implicit shared kernel dependencies
