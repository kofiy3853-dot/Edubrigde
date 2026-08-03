# ADR-158: University Domain Policies

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Domain Policies
**Decision:** University Domain Policies

## Context

EduBridge OS requires reusable business decision logic within the University bounded context. Without Domain Policies, business decisions would be scattered across entities, aggregates, or infrastructure, reducing consistency and reusability.

## Decision

The University bounded context adopts Domain Policies to encapsulate reusable business decision logic that cannot be naturally owned by a single entity or aggregate. Domain Policies evaluate business conditions and return business decisions without modifying state.

## Consequences

### Positive

- Centralized business decision logic
- Reusable across multiple services and aggregates
- Deterministic and testable
- Stateless design enables scalability
- Technology-independent design

### Negative

- Policy proliferation requires governance
- Policy composition adds complexity
- Policy boundaries require careful design

## Scope

### Owned
- University publication policies
- Institutional eligibility policies
- Program availability policies
- Admission requirement policies
- Accreditation validity policies
- Tuition consistency policies
- Campus operational policies
- Intake scheduling policies

### NOT Owned
- Application workflows
- Repository logic
- API logic
- Infrastructure logic

## Domain Policy Catalog
- UniversityPublicationPolicy
- AdmissionEligibilityPolicy
- AccreditationValidityPolicy
- TuitionConsistencyPolicy
- CampusOperationalPolicy
- IntakeSchedulingPolicy
- ProgramAvailabilityPolicy
- UniversityArchivalPolicy

## Implementation Rules
1. Policies are stateless
2. Policies return business decisions only
3. Policies never modify domain state
4. Infrastructure dependencies are prohibited
5. Policies use ubiquitous language
6. Business rules are centralized
7. Policies are independently testable
8. Policy duplication is prohibited
9. Domain integrity is preserved
10. Policy outcomes are consumed by aggregates or services

## NOT ALLOWED
- Policies modifying aggregates
- Policies persisting data
- Policies publishing events
- Policies accessing infrastructure
- Policies performing presentation logic
