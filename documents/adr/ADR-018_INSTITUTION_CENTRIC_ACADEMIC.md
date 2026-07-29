# ADR-018: Institution-Centric Academic Architecture

## Status
LOCKED — Permanent Architecture Decision

## Date
July 29, 2026

## Context

EduBridge OS requires an Academic Domain that serves as the single source of truth for all academic information. The platform connects students with universities, requiring a centralized academic architecture that prevents duplication, ensures consistency, and provides a foundation for all future academic services.

### The Problem

Without a centralized academic architecture, the following risks emerge:

```
RISK 1: University Domain → owns applications (DUPLICATE)
RISK 2: Scholarship Domain → owns programs (DUPLICATE)
RISK 3: AI Domain → owns universities (CROSS-DOMAIN)
RISK 4: Institution Domain → owns admission requirements (SCATTERED)
```

These patterns lead to:
- **Data duplication** — Same university information scattered across domains
- **Inconsistency** — Different domains have different versions of truth
- **Maintenance nightmare** — Changes require updates in multiple places
- **Integration complexity** — Every domain must sync with every other domain

### The Solution

Institution-Centric Academic Architecture establishes the Academic Domain as the permanent, single source of truth for all academic information.

```
Academic Domain (Single Source of Truth)
    │
    ├── Universities (own everything)
    │
    ├── Programs (belong to universities)
    │
    ├── Admission Requirements (belong to universities/programs)
    │
    ├── Academic Policies (validation rules)
    │
    └── Future Domains
        │
        ├── Applications → uses Academic Domain
        ├── Scholarships → uses Academic Domain
        ├── AI Services → uses Academic Domain
        └── Learning → uses Academic Domain
```

## Decision

**EduBridge OS will adopt an Institution-Centric Academic Architecture.**

### Core Principles

1. **Universities Own Programs**
   - Every program belongs to exactly one university
   - Universities are the root of the academic hierarchy
   - No program exists without a university

2. **Programs Own Admission Requirements**
   - Admission requirements belong to programs or universities
   - Requirements can be university-wide or program-specific
   - Requirements are independently configurable

3. **No Cross-Domain Duplication**
   - No future domain may duplicate academic information
   - All domains consume Academic Domain through APIs
   - Academic information exists in exactly one place

4. **Validation Layer**
   - All academic operations pass through validation policies
   - Validation rules are independently testable
   - Policies remain configurable per university/program

5. **Audit Layer**
   - Every academic operation is logged
   - Audit trail is permanent and immutable
   - Operations are traceable across domains

### Architecture Diagram

```
                    Academic Domain

                           │

                     Universities

                           │

                       Programs

                           │

                   Admission Requirements

                           │

                   Academic Policies

                           │

                      Audit Layer

                           │

                     Future Domains

┌─────────────────────────────────────────────────────────────────────┐
│                                                                     │
│  Applications    Scholarships    AI Services    Learning Services   │
│       │               │               │               │             │
│       └───────────────┴───────────────┴───────────────┘             │
│                           │                                         │
│                    uses Academic Domain                             │
│                           │                                         │
│                    APIs + Events                                    │
│                           │                                         │
│                    Academic Domain                                  │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### Entity Hierarchy

```
University (root)
    │
    ├── Program 1
    │   ├── Admission Requirement 1
    │   └── Admission Requirement 2
    │
    ├── Program 2
    │   ├── Admission Requirement 3
    │   └── Admission Requirement 4
    │
    └── University-Wide Admission Requirements
```

## Consequences

### Positive

1. **Single Source of Truth**
   - Academic information exists in exactly one place
   - No duplication, no inconsistency
   - Changes propagate automatically

2. **Simplified Integration**
   - Future domains consume through APIs
   - No cross-domain synchronization needed
   - Clean API boundaries

3. **Independent Testing**
   - Academic Domain is independently testable
   - Validation policies are testable in isolation
   - Audit logging is verifiable

4. **Extensibility**
   - New programs can be added to universities
   - New requirements can be added to programs
   - New policies can be added without affecting existing

5. **Security**
   - Academic operations are auditable
   - Validation prevents invalid data
   - Access control is centralized

### Negative

1. **Single Point of Failure**
   - Academic Domain failure affects all domains
   - Mitigation: High availability, caching, redundancy

2. **Complex Initial Setup**
   - Requires careful design of academic entities
   - Mitigation: Clear standards, phased implementation

3. **Performance Overhead**
   - All domains must call Academic APIs
   - Mitigation: Caching, read replicas, event-driven updates

## Compliance

### Required Standards

1. **University Ownership**
   - Universities are the root of academic hierarchy
   - Programs cannot exist without universities
   - Admission requirements belong to universities/programs

2. **No Cross-Domain Duplication**
   - No future domain may store university information
   - No future domain may store program information
   - All academic data flows through Academic Domain

3. **Validation First**
   - All academic operations pass through validation
   - Validation rules are independently configurable
   - Invalid operations are rejected

4. **Audit Everything**
   - Every academic operation is logged
   - Audit trail is permanent
   - Operations are traceable

### Forbidden Patterns

1. **Duplicate University Records**
   - BAD: Multiple domains storing same university
   - GOOD: Single Academic Domain as source of truth

2. **Cross-Domain Ownership**
   - BAD: Scholarship Domain managing programs
   - GOOD: Programs belong to Academic Domain

3. **Validation Bypass**
   - BAD: Direct database updates without validation
   - GOOD: All operations through Academic APIs

## References

- ADR-005: Backend Architecture (Modular Monolith)
- ADR-006: Database Architecture (9 Schemas)
- ADR-012: Identity Domain as Guardian Domain
- ACADEMIC_STANDARDS.md
