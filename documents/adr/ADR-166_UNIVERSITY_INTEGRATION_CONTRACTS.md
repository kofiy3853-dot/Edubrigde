# ADR-166: University Integration Contracts

**Status:** LOCKED
**Date:** 2026-07-31
**Domain:** University Integration Contracts
**Decision:** University Integration Contracts

## Context

EduBridge OS requires a structured public interface for cross-bounded-context communication. Without Integration Contracts, internal domain models would leak across architectural boundaries, reducing security and maintainability.

## Decision

The University bounded context exposes versioned Integration Contracts as its only public interface for cross-bounded-context communication. Internal domain models, aggregates, entities, and value objects MUST NEVER be exposed directly outside the University bounded context.

## Consequences

### Positive

- Clean public interface for cross-domain communication
- Versioned contracts enable safe evolution
- Anti-Corruption Layers prevent external concept leakage
- Domain autonomy preserved
- Backward compatibility maintained

### Negative

- Contract management adds operational overhead
- Versioning requires governance discipline
- ACL translation adds processing overhead

## Scope

### Owned
- University discovery contracts
- University profile contracts
- Academic program contracts
- Campus contracts
- Admission requirement contracts
- Tuition contracts
- Intake schedule contracts
- Accreditation contracts
- Institutional status contracts

### NOT Owned
- Transport protocols
- API gateways
- Message brokers
- Implementation technologies

## Integration Contract Catalog
- UniversityLookupContract
- UniversityProfileContract
- AcademicProgramCatalogContract
- AdmissionRequirementsContract
- TuitionInformationContract
- IntakeScheduleContract
- AccreditationStatusContract
- UniversityAvailabilityContract

## Implementation Rules
1. Integration Contracts are the only public interface
2. Internal aggregates are never exposed
3. Contracts are versioned
4. ACLs isolate external models
5. Business terminology is preserved
6. Contracts remain technology-independent
7. Backward compatibility is preferred
8. Breaking changes require new versions
9. Contracts are independently testable
10. Domain autonomy is preserved

## NOT ALLOWED
- Direct aggregate exposure to external domains
- Direct entity exposure to external domains
- Direct repository exposure to external domains
- Infrastructure metadata exposure
- Internal business rule exposure
