# ADR-140: Student Domain Business Policies

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Policy Foundations
**Decision:** Student Domain Business Policies

## Context

EduBridge OS requires explicit Business Policies to govern decisions that affect student behavior, lifecycle transitions, eligibility, profile completeness, and academic progression. Without explicit policies, business decisions would be embedded in services and entities, reducing traceability and governance.

## Decision

The Student Domain adopts explicit Business Policies to govern decisions that affect student behavior, lifecycle transitions, eligibility, profile completeness, and academic progression. Business Policies are domain-owned, versioned, independently testable, and evaluated consistently across the Student Domain.

## Consequences

### Positive

- Clear separation between business rules and execution logic
- Versioned policies enable controlled evolution
- Independent testability ensures policy correctness
- Auditable policy decisions support governance
- Consistent policy evaluation across the domain

### Negative

- Policy proliferation requires governance
- Deterministic evaluation limits some coordination patterns
- Policy versioning adds overhead

## Scope

### Owned
- Student lifecycle policies
- Profile completeness policies
- Academic progression policies
- Goal management policies
- Preference validation policies
- Student eligibility policies
- Student status transition policies

### NOT Owned
- Cross-domain eligibility (scholarships, admissions)
- Authentication policies
- Notification policies
- Financial policies
- Learning content policies

## Policy Categories
- Lifecycle Policies
- Profile Policies
- Academic Policies
- Goal Policies
- Preference Policies
- Eligibility Policies

## Policy Evaluation Outcomes
- Approved
- Rejected
- Requires Review
- Deferred

## NOT ALLOWED

- Policies containing infrastructure logic
- Policies persisting data
- Cross-domain policy ownership
- Non-deterministic policy evaluation
- Unauditable policy decisions
- Unversioned policies
