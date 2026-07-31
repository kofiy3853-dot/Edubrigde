# ADR-136: Student Entity as Aggregate Root

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Entity Foundations
**Decision:** Student Entity as Aggregate Root

## Context

EduBridge OS requires a clearly defined Aggregate Root for the Student Domain that represents the authoritative business identity of an individual using the platform as a student. Without an explicit Aggregate Root, student business operations would lack a single entry point and consistency boundary.

## Decision

The Student entity is the Aggregate Root of the Student Domain and represents the authoritative business identity of an individual using EduBridge OS as a student. The Student entity owns its business state, lifecycle, and invariants.

## Consequences

### Positive

- Clear single entry point for all student business operations
- Strong consistency for all student data modifications
- Clear encapsulation of student business rules
- Single source of truth for student state
- Domain event publication after successful state changes

### Negative

- Student entity complexity may grow with additional attributes
- Lifecycle management adds overhead
- Aggregate redesign may be required for new business requirements

## Scope

### Identity
- StudentId
- ProfileVersion
- CreatedAt
- UpdatedAt

### Personal Information
- Full Name
- Preferred Name
- Date of Birth
- Nationality
- Contact Information
- Preferred Language
- Time Zone

### Academic Profile
- Current Education Level
- Previous Institutions
- Areas of Study
- Academic Interests
- GPA or Equivalent
- Expected Graduation Date

### Education History
- Schools Attended
- Academic Milestones
- Certifications
- Completed Programs

### Learning Preferences
- Preferred Learning Style
- Preferred Content Format
- Preferred Study Schedule
- Accessibility Preferences
- Language Preferences

### Career Interests
- Preferred Career Fields
- Desired Industries
- Target Job Roles
- Long-Term Career Objectives

### Student Goals
- University Admission Goal
- Scholarship Goal
- Academic Performance Goal
- Certification Goal
- Skill Development Goal

### Business Status
- Prospect
- Applicant
- Active
- Inactive
- Suspended
- Alumni
- Archived

## NOT ALLOWED

- Authentication identity management within Student Domain
- Cross-domain ownership
- Bypassing aggregate invariants
- Direct state mutation without validation
- Publishing events before successful state changes
- Exposing entity internals to consumers
