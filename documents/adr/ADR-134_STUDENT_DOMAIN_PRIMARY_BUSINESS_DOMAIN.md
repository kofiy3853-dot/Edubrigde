# ADR-134: Student Domain as Primary Business Domain

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Foundations
**Decision:** Student Domain as Primary Business Domain

## Context

EduBridge OS requires a clearly defined primary business domain that owns all student-related business concepts, rules, and lifecycle operations. Without explicit Student Domain ownership, student-related responsibilities would fragment across bounded contexts.

## Decision

The Student Domain is the primary business domain of EduBridge OS. It owns all business concepts, rules, and lifecycle operations related to a student's identity, academic journey, educational preferences, and platform participation. No other bounded context may own or modify Student Domain business rules.

## Consequences

### Positive

- Clear ownership of all student-related business concepts
- Explicit boundary prevents student model pollution
- Integration contracts enable independent evolution
- Ubiquitous language ensures consistency within the domain
- Lifecycle states provide clear business state management

### Negative

- Student Domain overhead for simple student capabilities
- Cross-domain communication adds indirection
- Student aggregate complexity may grow over time

## Scope

### Owned
- Student Aggregate
- Student Profile
- Academic Profile
- Educational Background
- Student Preferences
- Student Goals
- Student Timeline
- Student Metadata

### NOT Owned
- Authentication
- University Information
- Scholarship Information
- Financial Records
- Learning Content
- Notifications
- Analytics
- Payments

## Student Aggregate Boundary

Primary Aggregate Root: Student

The Student Aggregate governs consistency for:
- Profile Information
- Academic Information
- Student Preferences
- Student Goals
- Student Status

External domains interact with the Student Aggregate only through published contracts or application services.

## Student Lifecycle States

- Prospect
- Applicant
- Active
- Suspended
- Alumni
- Archived

## Student Domain Events

- StudentRegistered
- StudentProfileUpdated
- AcademicProfileUpdated
- StudentGoalCreated
- StudentPreferenceUpdated
- StudentArchived

Only the Student Domain may publish these events.

## NOT ALLOWED

- Other bounded contexts owning student business rules
- Direct repository access across domains
- Shared mutable student models
- Unapproved student lifecycle state transitions
- Implicit student domain boundaries
- Student domain coupling to external domains
