# ADR-183: Admissions Integration Contracts

**Status:** LOCKED
**Date:** 2026-08-04
**Domain:** Admissions Integration Contracts
**Decision:** Admissions Integration Contract Catalog

## Context

EduBridge OS requires structured integration contracts for the Admissions bounded context. Without defined integration contracts, cross-boundary communication would lack standardization, versioning, and clear interfaces.

## Decision

The Admissions bounded context exposes versioned Integration Contracts as its only public interface for cross-bounded-context communication. Internal domain models are never exposed directly.

## Consequences

### Positive

- Clean public interface for cross-domain communication
- Versioned contracts enable safe evolution
- Anti-Corruption Layers prevent concept leakage
- Domain autonomy preserved
- Backward compatibility maintained

### Negative

- Contract management adds operational overhead
- Versioning requires governance discipline
- ACL translation adds processing overhead

## Scope

### Owned
- Application status contracts
- Eligibility result contracts
- Decision outcome contracts
- Offer status contracts
- Waitlist position contracts

### NOT Owned
- Transport protocols
- API gateways
- Message brokers

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
