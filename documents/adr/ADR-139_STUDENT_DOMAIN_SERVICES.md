# ADR-139: Student Domain Services

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Domain Service Foundations
**Decision:** Student Domain Services

## Context

EduBridge OS requires stateless Domain Services to encapsulate business operations that involve multiple entities, value objects, or business policies but do not naturally belong within a single aggregate component. Without Domain Services, complex business logic would be forced into aggregate roots, reducing clarity and maintainability.

## Decision

The Student Domain uses Domain Services to encapsulate stateless business operations that involve multiple entities, value objects, or business policies but do not naturally belong within a single aggregate component. Domain Services contain business rules, not infrastructure concerns.

## Consequences

### Positive

- Clear separation between stateful aggregate and stateless business logic
- Reusable business operations across multiple entities
- Infrastructure-independent domain logic
- Explicit service contracts for business operations
- Policy coordination without aggregate pollution

### Negative

- Service proliferation may occur without governance
- Stateless design limits some coordination patterns
- Service contracts require versioning discipline

## Scope

### Owned
- Student business services
- Student business policies
- Student domain calculations
- Student business validations
- Student lifecycle coordination

### NOT Owned
- Authentication services
- Notification delivery
- Scholarship processing
- Financial planning
- Learning content management
- Infrastructure services

## Approved Domain Services
- StudentProfileService
- AcademicProgressService
- StudentGoalService
- StudentEligibilityService
- StudentLifecycleService
- StudentPreferenceService

## NOT ALLOWED

- Domain Services persisting data directly
- Domain Services calling infrastructure
- Domain Services managing transactions
- Domain Services sending notifications
- Domain Services accessing external APIs
- Domain Services owning business state
- Cross-domain ownership
