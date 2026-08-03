# ADR-159: University Domain Events

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Domain Events
**Decision:** University Domain Events

## Context

EduBridge OS requires a mechanism to represent completed business facts within the University bounded context. Without Domain Events, the University Aggregate would lack the ability to communicate state changes asynchronously to other bounded contexts.

## Decision

The University bounded context adopts Domain Events to represent immutable business facts that have already occurred. A Domain Event is published only after a successful business operation has committed. Events communicate completed business facts and enable asynchronous collaboration with other bounded contexts.

## Consequences

### Positive

- Immutable events ensure consistency
- Asynchronous collaboration enables scalability
- Event-driven architecture supports loose coupling
- Traceable business facts
- Technology-independent design

### Negative

- Event proliferation requires governance
- Event ordering requires management
- Event versioning adds complexity

## Scope

### Owned
- University lifecycle events
- Academic program events
- Campus events
- Admission requirement events
- Tuition structure events
- Accreditation events
- Intake period events
- Institutional publication events

### NOT Owned
- Messaging infrastructure
- Event brokers
- Transport protocols
- Cross-domain event publishing

## Domain Event Catalog
- UniversityRegistered
- UniversityVerified
- UniversityPublished
- UniversityProfileUpdated
- CampusAdded
- AcademicProgramAdded
- AdmissionRequirementsUpdated
- TuitionStructureUpdated
- AccreditationUpdated
- IntakePeriodScheduled
- UniversityArchived

## Implementation Rules
1. Events represent completed business facts
2. Events are immutable
3. Aggregate Root publishes events
4. Entities never publish events directly
5. Event metadata is mandatory
6. Events remain technology-independent
7. Ordering is preserved within an aggregate
8. Event versioning is mandatory
9. Business terminology is used consistently
10. Domain integrity is maintained

## NOT ALLOWED
- Entities publishing events directly
- Events representing future intentions
- Mutable events
- Events without metadata
- Infrastructure-dependent events
