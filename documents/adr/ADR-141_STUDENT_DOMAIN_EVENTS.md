# ADR-141: Student Domain Events

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Event Foundations
**Decision:** Student Domain Events

## Context

EduBridge OS requires Domain Events to communicate completed business facts to other bounded contexts. Without Domain Events, cross-domain integration would require direct model access, leading to tight coupling and inconsistent state.

## Decision

The Student Domain adopts Domain Events to communicate completed business facts to other bounded contexts. A Domain Event represents something that has already occurred within the Student Aggregate and cannot be modified after publication.

## Consequences

### Positive

- Loose coupling between bounded contexts
- Immutable historical facts for auditability
- Event-driven integration enables independent evolution
- Standardized metadata ensures consistency
- Versioning supports controlled evolution

### Negative

- Event proliferation requires governance
- Eventually consistent model limits some queries
- Event versioning adds overhead

## Scope

### Lifecycle Events
- StudentRegistered
- StudentActivated
- StudentSuspended
- StudentReactivated
- StudentArchived
- StudentStatusChanged

### Profile Events
- StudentProfileCreated
- StudentProfileUpdated
- StudentContactUpdated
- StudentPreferenceUpdated

### Academic Events
- AcademicProfileCreated
- AcademicProfileUpdated
- EducationRecordAdded
- EducationRecordUpdated
- AcademicMilestoneRecorded

### Goal Events
- StudentGoalCreated
- StudentGoalUpdated
- StudentGoalCompleted
- StudentGoalArchived

### Preference Events
- LearningPreferenceUpdated
- AccessibilityPreferenceUpdated
- StudyScheduleUpdated

## NOT ALLOWED

- Events representing commands or future intentions
- Mutable events after publication
- Non-Aggregate Root entities publishing events
- Events published before successful state changes
- Events without standardized metadata
- Unversioned events
- External domains modifying Student Aggregate state
