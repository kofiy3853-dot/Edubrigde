# ADR-020: Global Academic Program Architecture

## Status
LOCKED — Permanent Architecture Decision

## Date
July 29, 2026

## Context

EduBridge OS requires a Program Management Module that serves as the single source of truth for all academic program information. Programs are first-class academic entities with their own academic standards, requirements, durations, levels, and lifecycle policies.

### The Problem

Without centralized program management, the following risks emerge:

```
RISK 1: Applications Domain → manages programs (SCATTERED)
RISK 2: AI Services → manages programs (CROSS-DOMAIN)
RISK 3: Scholarship Domain → manages programs (DUPLICATE)
RISK 4: Learning Domain → manages programs (INCONSISTENT)
```

These patterns lead to:
- **Data duplication** — Same program information scattered across domains
- **Inconsistency** — Different domains have different versions of truth
- **Maintenance nightmare** — Changes require updates in multiple places
- **Integration complexity** — Every domain must sync with every other domain
- **Validation bypass** — No centralized validation of program information

### The Solution

Global Academic Program Architecture establishes the Program Management Module as the permanent, single source of truth for all program information.

```
Academic Domain
    │
    ├── University Module
    │
    └── Program Management Module
        │
        ├── Program Catalog
        │
        ├── Academic Levels
        │
        ├── Program Requirements
        │
        ├── Duration Policies
        │
        ├── Program Lifecycle
        │
        └── Future Domains
            │
            ├── Applications → uses Program Module
            ├── Scholarships → uses Program Module
            ├── AI Services → uses Program Module
            └── Learning → uses Program Module
```

## Decision

**EduBridge OS will adopt a Global Academic Program Architecture.**

### Core Principles

1. **Programs are First-Class Entities**
   - Programs have their own academic standards
   - Programs have their own requirements
   - Programs have their own lifecycle
   - Programs are not mere records inside universities

2. **Academic Levels are Standardized**
   - Academic levels are defined at the system level
   - Academic levels have duration standards
   - Academic levels have requirement policies
   - Academic levels affect program capabilities

3. **Requirements are Program-Specific**
   - Each program has its own requirements
   - Requirements can be mandatory or optional
   - Requirements have validation rules
   - Requirements are independently manageable

4. **Duration Policies are Configurable**
   - Each program has duration policies
   - Duration policies support different modes (full-time, part-time, etc.)
   - Duration policies have extension rules
   - Duration policies are independently testable

5. **Lifecycle is Tracked**
   - Every program has a lifecycle state
   - Lifecycle changes are logged
   - Lifecycle affects program capabilities
   - Lifecycle is independently manageable

### Architecture Diagram

```
                    Academic Domain
                           │
                    University Module
                           │
                    Program Management
                           │
                    Program Catalog
                           │
                    Academic Levels
                           │
                    Program Requirements
                           │
                    Duration Policies
                           │
                    Program Lifecycle
                           │
                    Validation Layer
                           │
                    Audit Layer
                           │
                    Future Domains

┌─────────────────────────────────────────────────────────────────────┐
│                                                                     │
│  Applications    Scholarships    AI Services    Financial Services  │
│       │               │               │               │             │
│       └───────────────┴───────────────┴───────────────┘             │
│                           │                                         │
│                  uses Program Module                                │
│                           │                                         │
│                  APIs + Events                                      │
│                           │                                         │
│                  Program Module                                     │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### Entity Structure

```
Program (root)
    │
    ├── Academic Level
    │   └── Level-specific standards
    │
    ├── Program Requirements
    │   ├── Academic Requirements
    │   ├── Language Requirements
    │   ├── Experience Requirements
    │   └── Document Requirements
    │
    ├── Duration Policies
    │   ├── Full-Time Policy
    │   ├── Part-Time Policy
    │   ├── Accelerated Policy
    │   └── Flexible Policy
    │
    └── Program Lifecycle
        ├── Current Status
        ├── Status History
        └── Review Records
```

## Consequences

### Positive

1. **Single Source of Truth**
   - Program information exists in exactly one place
   - No duplication, no inconsistency
   - Changes propagate automatically

2. **Centralized Validation**
   - All program operations pass through validation
   - Academic levels are enforced
   - Requirements are validated

3. **Simplified Integration**
   - Future domains consume through APIs
   - No cross-domain synchronization needed
   - Clean API boundaries

4. **Independent Testing**
   - Program Management Module is independently testable
   - Academic levels are verifiable
   - Requirements are testable in isolation

5. **Security**
   - Program operations are auditable
   - Access control is centralized
   - Changes are tracked

### Negative

1. **Single Point of Failure**
   - Program Module failure affects all domains
   - Mitigation: High availability, caching, redundancy

2. **Complex Initial Setup**
   - Requires careful design of program entities
   - Mitigation: Clear standards, phased implementation

3. **Performance Overhead**
   - All domains must call Program APIs
   - Mitigation: Caching, read replicas, event-driven updates

## Compliance

### Required Standards

1. **Program Catalog**
   - Every program must have validated program information
   - Programs must be extensible
   - Programs must be independently testable

2. **Academic Levels**
   - Academic levels must be standardized
   - Academic levels must have duration standards
   - Academic levels must affect program capabilities

3. **Program Requirements**
   - Requirements must be program-specific
   - Requirements must have validation rules
   - Requirements must be independently manageable

4. **Duration Policies**
   - Duration policies must be configurable
   - Duration policies must have extension rules
   - Duration policies must be independently testable

5. **Program Lifecycle**
   - Every program must have a lifecycle state
   - Lifecycle changes must be logged
   - Lifecycle must affect program capabilities

### Forbidden Patterns

1. **Duplicate Program Records**
   - BAD: Multiple domains storing same program
   - GOOD: Single Program Module as source of truth

2. **Cross-Domain Ownership**
   - BAD: Applications Domain managing programs
   - GOOD: Programs belong to Program Module

3. **Requirement Bypass**
   - BAD: Direct database updates without validation
   - GOOD: All operations through Program APIs

4. **Unauthorized Modifications**
   - BAD: Unauthenticated access to program information
   - GOOD: Validated access through Program Module

## References

- ADR-005: Backend Architecture (Modular Monolith)
- ADR-006: Database Architecture (9 Schemas)
- ADR-012: Identity Domain as Guardian Domain
- ADR-018: Institution-Centric Academic Architecture
- ADR-019: Global Institution Management Architecture
- PROGRAM_MANAGEMENT_STANDARDS.md
