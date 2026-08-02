# ADR-157: University Domain Services

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Domain Services
**Decision:** University Domain Services

## Context

EduBridge OS requires business operations that span multiple entities, value objects, or business policies within the University bounded context. Without Domain Services, cross-entity coordination would be embedded in aggregates or infrastructure, reducing testability and separation of concerns.

## Decision

The University bounded context adopts Domain Services to coordinate business operations that span multiple entities, value objects, or business policies. Domain Services contain business logic that cannot be naturally assigned to the University Aggregate or an individual entity while remaining free from infrastructure concerns.

## Consequences

### Positive

- Clear separation of cross-entity business logic
- Stateless services enable scalability
- Reusable coordination logic
- Technology-independent design
- Business-focused operations

### Negative

- Service proliferation requires governance
- Indirection between application and domain layers
- Service boundaries require careful design

## Scope

### Owned
- University registration coordination
- Program management coordination
- Admission requirement coordination
- Tuition coordination
- Accreditation verification
- Intake planning coordination
- Campus management coordination
- University publication coordination

### NOT Owned
- Aggregate responsibilities
- Entity responsibilities
- Infrastructure interactions
- Cross-domain workflows

## Domain Service Catalog
- UniversityRegistrationService
- ProgramManagementService
- AdmissionRequirementService
- TuitionManagementService
- AccreditationService
- IntakePlanningService
- CampusManagementService
- UniversityPublicationService

## Implementation Rules
1. Domain Services are stateless
2. Services coordinate business operations across multiple domain objects
3. Aggregate responsibilities are not duplicated
4. Infrastructure dependencies are prohibited
5. Services use ubiquitous language
6. Business rules remain within the domain layer
7. Persistence is managed outside Domain Services
8. Services are independently testable
9. Cross-domain communication uses Integration Contracts
10. Domain integrity is preserved

## NOT ALLOWED
- Domain Services storing business state
- Domain Services accessing infrastructure directly
- Domain Services replacing aggregate responsibilities
- Domain Services managing authentication
- Domain Services executing presentation logic
