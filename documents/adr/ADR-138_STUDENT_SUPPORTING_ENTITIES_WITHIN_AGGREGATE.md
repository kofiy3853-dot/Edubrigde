# ADR-138: Student Supporting Entities Within Aggregate

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Entity Foundations
**Decision:** Student Supporting Entities Within Aggregate

## Context

EduBridge OS requires supporting entities within the Student Aggregate that represent business concepts requiring their own identity but whose lifecycle is completely controlled by the Student Aggregate Root. Without supporting entities, complex student data would be flattened into the aggregate root, reducing clarity and maintainability.

## Decision

The Student Aggregate contains supporting entities that represent business concepts requiring their own identity but whose lifecycle is completely controlled by the Student Aggregate Root. Supporting entities cannot exist independently outside the Student Aggregate.

## Consequences

### Positive

- Clear separation of concerns within the aggregate
- Independent identity for complex business concepts
- Aggregate root maintains full lifecycle control
- Transactional consistency preserved
- Encapsulation rules prevent unauthorized access

### Negative

- Supporting entity complexity may grow over time
- Aggregate root coordination adds indirection
- Supporting entity redesign may be required for new business requirements

## Scope

### Supporting Entities
- EducationRecord
- AcademicMilestone
- StudentGoal
- StudentPreference

### Entity Identity Requirements
- Each supporting entity MUST have a unique identifier within the aggregate
- Entity identifiers MUST be immutable after creation
- Entity identifiers MUST be documented
- Entity identifiers MUST be versioned
- Entity identifier changes MUST be approved

## NOT ALLOWED

- Supporting entities existing independently outside the Student Aggregate
- Direct modification of supporting entities by external components
- Supporting entities publishing domain events independently
- Supporting entities accessing external repositories
- Supporting entities coordinating other aggregates
- Supporting entities communicating directly with other bounded contexts
