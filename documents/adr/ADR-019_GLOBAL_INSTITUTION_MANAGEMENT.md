# ADR-019: Global Institution Management Architecture

## Status
LOCKED — Permanent Architecture Decision

## Date
July 29, 2026

## Context

EduBridge OS requires a University Management Module that serves as the single source of truth for all institutional information. Universities are globally managed academic institutions with validated institutional information, accreditation records, partnership relationships, academic classifications, and international metadata.

### The Problem

Without centralized university management, the following risks emerge:

```
RISK 1: Applications Domain → manages universities (SCATTERED)
RISK 2: Scholarship Domain → manages universities (DUPLICATE)
RISK 3: AI Services → manages universities (CROSS-DOMAIN)
RISK 4: Learning Domain → manages universities (INCONSISTENT)
```

These patterns lead to:
- **Data duplication** — Same university information scattered across domains
- **Inconsistency** — Different domains have different versions of truth
- **Maintenance nightmare** — Changes require updates in multiple places
- **Integration complexity** — Every domain must sync with every other domain
- **Validation bypass** — No centralized validation of institutional information

### The Solution

Global Institution Management Architecture establishes the University Management Module as the permanent, single source of truth for all institutional information.

```
Academic Domain
    │
    └── University Management Module
        │
        ├── University Profiles
        │
        ├── Accreditation Records
        │
        ├── Institution Classifications
        │
        ├── International Partnerships
        │
        ├── Institutional Policies
        │
        └── Future Domains
            │
            ├── Applications → uses University Module
            ├── Scholarships → uses University Module
            ├── AI Services → uses University Module
            └── Learning → uses University Module
```

## Decision

**EduBridge OS will adopt a Global Institution Management Architecture.**

### Core Principles

1. **Universities as Global Entities**
   - Universities are globally managed academic institutions
   - Every university possesses validated institutional information
   - Institutional information exists in exactly one place

2. **Accreditation is Central**
   - Every university must have accreditation records
   - Accreditation records are independently verifiable
   - Accreditation status affects university capabilities

3. **Classifications are Configurable**
   - Institution classifications are assigned by authorized bodies
   - Classifications affect university policies and capabilities
   - Classifications can be changed with proper validation

4. **Partnerships are Managed**
   - International partnerships are formal relationships
   - Partnerships have lifecycle management
   - Partnerships affect university capabilities

5. **Policies are Enforceable**
   - Institutional policies are enforced at the system level
   - Policies are independently testable
   - Policies affect all university operations

### Architecture Diagram

```
                    Academic Domain
                           │
                    University Module
                           │
                    University Profiles
                           │
                    Accreditation Records
                           │
                    Institution Classifications
                           │
                    International Partnerships
                           │
                    Institutional Policies
                           │
                    Validation Layer
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
│                  uses University Module                             │
│                           │                                         │
│                  APIs + Events                                      │
│                           │                                         │
│                  University Module                                  │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### Entity Structure

```
University Profile (root)
    │
    ├── Accreditation Records
    │   ├── Institutional Accreditation
    │   ├── Academic Accreditation
    │   ├── Country Accreditation
    │   └── Partnership Accreditation
    │
    ├── Institution Classifications
    │   ├── Size Classification
    │   ├── Research Classification
    │   ├── Focus Classification
    │   ├── Region Classification
    │   └── Network Classification
    │
    ├── International Partnerships
    │   ├── Exchange Programs
    │   ├── Strategic Partnerships
    │   ├── Academic Partnerships
    │   └── Dual Degree Programs
    │
    └── Institutional Policies
        ├── Admission Policies
        ├── Academic Policies
        ├── Institution Policies
        ├── Validation Policies
        └── Integration Policies
```

## Consequences

### Positive

1. **Single Source of Truth**
   - Institutional information exists in exactly one place
   - No duplication, no inconsistency
   - Changes propagate automatically

2. **Centralized Validation**
   - All institutional operations pass through validation
   - Accreditation is independently verifiable
   - Classifications are enforced

3. **Simplified Integration**
   - Future domains consume through APIs
   - No cross-domain synchronization needed
   - Clean API boundaries

4. **Independent Testing**
   - University Management Module is independently testable
   - Accreditation records are verifiable
   - Policies are testable in isolation

5. **Security**
   - Institutional operations are auditable
   - Access control is centralized
   - Changes are tracked

### Negative

1. **Single Point of Failure**
   - University Module failure affects all domains
   - Mitigation: High availability, caching, redundancy

2. **Complex Initial Setup**
   - Requires careful design of institutional entities
   - Mitigation: Clear standards, phased implementation

3. **Performance Overhead**
   - All domains must call University APIs
   - Mitigation: Caching, read replicas, event-driven updates

## Compliance

### Required Standards

1. **University Profiles**
   - Every university must have validated institutional information
   - Profiles must be extensible
   - Profiles must be independently testable

2. **Accreditation Records**
   - Every university must have accreditation records
   - Accreditation records must be independently verifiable
   - Accreditation status must affect university capabilities

3. **Institution Classifications**
   - Classifications must be assigned by authorized bodies
   - Classifications must be configurable
   - Classifications must affect university capabilities

4. **International Partnerships**
   - Partnerships must be formal relationships
   - Partnerships must have lifecycle management
   - Partnerships must affect university capabilities

5. **Institutional Policies**
   - Policies must be enforced at the system level
   - Policies must be independently testable
   - Policies must affect all university operations

### Forbidden Patterns

1. **Duplicate University Records**
   - BAD: Multiple domains storing same university
   - GOOD: Single University Module as source of truth

2. **Cross-Domain Ownership**
   - BAD: Applications Domain managing universities
   - GOOD: Universities belong to University Module

3. **Accreditation Bypass**
   - BAD: Direct database updates without validation
   - GOOD: All operations through University APIs

4. **Unauthorized Modifications**
   - BAD: Unauthenticated access to university information
   - GOOD: Validated access through University Module

## References

- ADR-005: Backend Architecture (Modular Monolith)
- ADR-006: Database Architecture (9 Schemas)
- ADR-012: Identity Domain as Guardian Domain
- ADR-018: Institution-Centric Academic Architecture
- UNIVERSITY_MANAGEMENT_STANDARDS.md
