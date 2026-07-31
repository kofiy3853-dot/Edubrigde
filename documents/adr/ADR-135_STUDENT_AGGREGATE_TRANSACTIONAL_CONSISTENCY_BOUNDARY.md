# ADR-135: Student Aggregate as Transactional Consistency Boundary

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Aggregate Foundations
**Decision:** Student Aggregate as Transactional Consistency Boundary

## Context

EduBridge OS requires a clearly defined consistency boundary for all student business operations. Without an aggregate, student data modifications would lack transactional integrity, leading to inconsistent state across bounded contexts.

## Decision

The Student Aggregate is the transactional consistency boundary for all student business operations. Every modification to student-owned business data MUST occur through the Student Aggregate Root.

## Consequences

### Positive

- Strong consistency for all student data modifications
- Clear encapsulation of student business rules
- Single source of truth for student state
- Transactional integrity across aggregate operations
- Domain event publication after successful state changes

### Negative

- Aggregate complexity may grow with additional entities
- Consistency boundary may limit concurrent modifications
- Aggregate redesign may be required for new business requirements

## Scope

### Aggregate Root
- Student

### Internal Entities
- EducationRecord
- AcademicMilestone
- StudentGoal

### Value Objects
- StudentName
- EmailAddress
- PhoneNumber
- PostalAddress
- DateOfBirth
- Nationality
- AcademicLevel
- GradePointAverage
- LearningPreference
- CareerInterest
- GoalPriority

### Aggregate Invariants
- A valid Student Profile always exists
- Student identity remains unique
- Education records are internally consistent
- Goals belong to the owning student
- Academic milestones are chronologically valid
- Preferences remain associated with the owning student
- Invalid state transitions are rejected

## NOT ALLOWED

- External components bypassing the Aggregate Root
- Partial modifications to aggregate state
- Direct modification of child entities
- Compromising aggregate consistency
- Publishing events before successful state changes
- Exposing aggregate internals to consumers
