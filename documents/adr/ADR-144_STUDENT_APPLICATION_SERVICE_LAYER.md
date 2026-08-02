# ADR-144: Student Application Service Layer

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Student Application Service Foundations
**Decision:** Student Application Service Layer

## Context

EduBridge OS requires an Application Service Layer to coordinate use cases involving the Student Aggregate. Without Application Services, use case orchestration would be embedded in controllers or domain logic, reducing testability and separation of concerns.

## Decision

The Student bounded context adopts an Application Service Layer responsible for coordinating use cases involving the Student Aggregate. Application Services orchestrate interactions between the domain model, repositories, and infrastructure while remaining free of business decision logic.

## Consequences

### Positive

- Clear separation between use case orchestration and business logic
- Stateless services enable scalability
- Transaction-aware design ensures consistency
- Infrastructure-coordinating pattern enables flexibility
- Independently testable without infrastructure dependencies

### Negative

- Service proliferation requires governance
- Transaction boundary management adds complexity
- Indirection between client and domain reduces directness

## Scope

### Owned
- Student registration use cases
- Student profile management
- Student goal-related use cases
- Student academic profile management
- Student preference management
- Student lifecycle transitions

### NOT Owned
- Business rules (owned by Aggregate, Domain Services, Domain Policies)
- Persistence logic (owned by Repository)
- Infrastructure behavior
- REST API endpoints
- Messaging endpoints

## Application Service Catalog
- StudentRegistrationApplicationService
- StudentProfileApplicationService
- StudentGoalApplicationService
- StudentAcademicApplicationService
- StudentPreferenceApplicationService
- StudentLifecycleApplicationService

## NOT ALLOWED

- Application Services containing business rules
- Application Services bypassing aggregate validation
- Application Services modifying repositories directly outside repository contracts
- Application Services performing persistence logic
- Application Services implementing infrastructure behavior
- Cross-domain persistence
