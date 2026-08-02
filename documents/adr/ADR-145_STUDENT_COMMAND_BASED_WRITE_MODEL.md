# ADR-145: Student Command-Based Write Model

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Commands and Command Handlers
**Decision:** Student Command-Based Write Model

## Context

EduBridge OS requires a structured write-side architecture for the Student Aggregate. Without a command-based model, use cases for state changes would lack consistency, testability, and traceability.

## Decision

The Student bounded context adopts a Command-Based Write Model. Commands represent an intention to change the Student Aggregate. Command Handlers coordinate execution by invoking Application Services while preserving aggregate consistency.

## Consequences

### Positive

- Immutable commands ensure traceability
- Single aggregate targeting preserves transactional consistency
- Structural validation precedes execution
- Transport-independent design enables flexibility
- Command contracts are versionable and testable

### Negative

- Command proliferation requires governance
- Handler indirection adds indirection between client and domain

## Scope

### Owned
- Student registration commands
- Student profile update commands
- Student academic profile update commands
- Student goal management commands
- Student preference update commands
- Student lifecycle transition commands

### NOT Owned
- Business rules (owned by Aggregate, Domain Services, Domain Policies)
- Infrastructure transport
- Domain event publishing
- Persistence logic

## Command Catalog
- RegisterStudentCommand
- UpdateStudentProfileCommand
- UpdateStudentContactCommand
- UpdateAcademicProfileCommand
- AddEducationRecordCommand
- RecordAcademicMilestoneCommand
- CreateStudentGoalCommand
- UpdateStudentGoalCommand
- CompleteStudentGoalCommand
- ArchiveStudentGoalCommand
- UpdateLearningPreferenceCommand
- UpdateAccessibilityPreferenceCommand
- UpdateStudyScheduleCommand
- ActivateStudentCommand
- SuspendStudentCommand
- ReactivateStudentCommand
- ArchiveStudentCommand

## Implementation Rules
1. Commands are immutable
2. Commands contain no business logic
3. Command Handlers orchestrate execution only
4. Business rules remain in the domain layer
5. Structural validation precedes execution
6. One command targets one aggregate
7. Domain events published only after successful completion
8. Handlers depend on abstractions
9. Infrastructure remains outside the application layer
10. Command contracts are versioned

## NOT ALLOWED
- Commands containing business logic
- Handlers modifying aggregates directly
- Handlers accessing databases directly
- Handlers publishing domain events independently
- Commands targeting multiple aggregates
