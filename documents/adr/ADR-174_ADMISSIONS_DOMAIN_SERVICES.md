# ADR-174: Admissions Domain Services

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** Admissions Domain Services
**Decision:** Admissions Domain Service Catalog

## Context

EduBridge OS requires structured domain services for the Admissions bounded context. Without defined domain services, business operations spanning multiple entities would lack coordination, stateless execution, and proper separation of concerns.

## Decision

The Admissions bounded context adopts Domain Services to coordinate business operations that span multiple entities, value objects, and domain policies without violating aggregate responsibilities. Domain Services encapsulate business behavior that cannot be naturally assigned to a single Aggregate or Entity.

## Consequences

### Positive

- Stateless services ensure thread safety
- Cross-entity coordination without aggregate violation
- Business logic encapsulated in services
- Technology-independent design
- Independently testable components

### Negative

- Service proliferation requires governance
- Stateless design limits state management options
- Service boundaries require careful definition

## Scope

### Owned
- AdmissionEligibilityService
- AdmissionEvaluationService
- AdmissionDecisionService
- AdmissionOfferService
- WaitlistManagementService
- AdmissionLifecycleService
- AdmissionTimelineService
- EnrollmentReadinessService

### NOT Owned
- Repositories
- Application services
- Persistence
- Infrastructure
- Presentation logic

## Domain Service Catalog
- AdmissionEligibilityService: Coordinates eligibility verification
- AdmissionEvaluationService: Coordinates academic/administrative evaluations
- AdmissionDecisionService: Coordinates final admission decisions
- AdmissionOfferService: Coordinates admission offer generation
- WaitlistManagementService: Coordinates waitlist operations
- AdmissionLifecycleService: Coordinates lifecycle transitions
- AdmissionTimelineService: Maintains chronological admission history
- EnrollmentReadinessService: Determines enrollment readiness

## Implementation Rules
1. Domain Services are stateless
2. Domain Services coordinate complex business operations
3. Aggregates retain responsibility for business invariants
4. Domain Services do not own state
5. Infrastructure dependencies are prohibited
6. Business terminology is preserved
7. Services are independently testable
8. Aggregate boundaries are respected
9. Domain Policies may be invoked as needed
10. Domain integrity is maintained

## NOT ALLOWED
- Domain Service access to repositories
- Domain Service access to databases
- Domain Service access to REST APIs
- Domain Service access to presentation layer
- Domain Service state ownership
- Domain Service transaction management
