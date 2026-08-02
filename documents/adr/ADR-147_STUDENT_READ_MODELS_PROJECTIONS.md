# ADR-147: Student Read Models and Projections

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Read Models and Projections
**Decision:** Student Read Models and Projections

## Context

EduBridge OS requires optimized, query-focused representations of Student data for the read side. Without dedicated read models and projections, query operations would depend on aggregate structures, reducing query performance and flexibility.

## Decision

The Student bounded context adopts Read Models and Projections to provide optimized, query-focused representations of Student data. Read Models are derived from Student Domain Events and are not authoritative business objects. The Student Aggregate remains the single source of truth.

## Consequences

### Positive

- Optimized read models for efficient querying
- Event-driven projections ensure consistency
- Denormalized structures enable fast lookups
- Independent versioning supports evolution
- Aggregate remains the authoritative source

### Negative

- Projection proliferation requires governance
- Read model maintenance adds overhead
- Event replay complexity increases

## Scope

### Owned
- Student profile summaries
- Academic summaries
- Education history views
- Goal summaries
- Learning preferences
- Student timelines
- Lifecycle summaries

### NOT Owned
- Business rules (owned by Aggregate, Domain Services, Domain Policies)
- Aggregate state (single source of truth)
- Domain event definition
- Infrastructure event processing

## Read Model Catalog
- StudentProfileView
- StudentSummaryView
- StudentContactView
- AcademicProfileView
- EducationHistoryView
- AcademicMilestoneView
- StudentGoalView
- GoalProgressView
- CompletedGoalView
- LearningPreferenceView
- AccessibilityPreferenceView
- StudyScheduleView
- StudentTimelineView
- StudentActivityView
- StudentLifecycleView

## Projection Catalog
- StudentProfileProjection
- AcademicProjection
- StudentGoalProjection
- StudentTimelineProjection

## Implementation Rules
1. Read Models are read-only
2. Projections are event-driven
3. Projections are idempotent
4. Read Models are optimized for queries
5. Aggregate structure remains independent
6. Business logic remains in the domain layer
7. Projection replay is supported
8. Event ownership is preserved
9. Read-side independence is mandatory
10. CQRS separation is maintained

## NOT ALLOWED
- Projections modifying aggregate state
- Projections executing business logic
- External bounded contexts updating Student projections
- Read models as authoritative business objects
- Projection bypass of version compatibility policies
