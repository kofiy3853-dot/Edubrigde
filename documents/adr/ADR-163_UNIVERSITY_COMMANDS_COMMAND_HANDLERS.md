# ADR-163: University Commands & Command Handlers

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Commands & Command Handlers
**Decision:** University Commands & Command Handlers

## Context

EduBridge OS requires a structured write-side architecture for the University bounded context. Without Commands and Command Handlers, write operations would lack consistency, testability, and traceability.

## Decision

The University bounded context adopts Commands and Command Handlers as the write-side implementation of CQRS. Commands represent a request to perform a business operation. Command Handlers validate the request, coordinate the application layer, invoke the University Domain Model, and persist successful changes.

## Consequences

### Positive

- Immutable commands ensure traceability
- Dedicated handlers enforce single responsibility
- Layered validation ensures correctness
- Optimistic concurrency prevents conflicts
- Technology-independent design enables flexibility

### Negative

- Command proliferation requires governance
- Handler indirection adds complexity
- Validation layering requires careful design

## Scope

### Owned
- University registration commands
- University profile management commands
- Campus management commands
- Academic program management commands
- Admission requirement commands
- Tuition management commands
- Accreditation update commands
- Intake management commands
- University publication commands
- University archival commands

### NOT Owned
- Query operations (read side)
- Read models
- Messaging infrastructure
- API endpoints

## Command Catalog
- RegisterUniversityCommand
- UpdateUniversityProfileCommand
- AddCampusCommand
- UpdateCampusCommand
- AddAcademicProgramCommand
- UpdateAdmissionRequirementsCommand
- UpdateTuitionStructureCommand
- UpdateAccreditationCommand
- ScheduleIntakePeriodCommand
- PublishUniversityCommand
- ArchiveUniversityCommand

## Implementation Rules
1. Commands represent business intent only
2. Commands are immutable
3. One command maps to one handler
4. Command Handlers are stateless
5. Business rules remain in the domain
6. Validation is layered
7. Optimistic concurrency is enforced
8. Commands are technology-independent
9. Domain events originate from aggregates
10. Domain integrity is preserved

## NOT ALLOWED
- Business logic in Commands
- Business logic in Command Handlers
- Multiple handlers for one command
- Mutable commands
- Infrastructure dependencies in commands
