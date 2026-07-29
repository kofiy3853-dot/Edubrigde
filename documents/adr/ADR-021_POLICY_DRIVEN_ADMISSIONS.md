# ADR-021: Policy-Driven Admissions Architecture

## Status
LOCKED — Permanent Architecture Decision

## Date
July 29, 2026

## Context

EduBridge OS requires an Admissions Management Module that serves as the single source of truth for all admission decisions. Admission is not a single requirement—it is the result of academic validation, institutional policies, program requirements, country-specific regulations, and eligibility assessments.

### The Problem

Without centralized admissions management, the following risks emerge:

```
RISK 1: Applications Domain → makes admission decisions (SCATTERED)
RISK 2: Scholarship Domain → makes admission decisions (DUPLICATE)
RISK 3: AI Services → makes admission decisions (CROSS-DOMAIN)
RISK 4: Learning Domain → makes admission decisions (INCONSISTENT)
```

These patterns lead to:
- **Data duplication** — Same admission decisions scattered across domains
- **Inconsistency** — Different domains have different admission outcomes
- **Maintenance nightmare** — Changes require updates in multiple places
- **Integration complexity** — Every domain must sync with every other domain
- **Validation bypass** — No centralized validation of admission decisions

### The Solution

Policy-Driven Admissions Architecture establishes the Admissions Management Module as the permanent, single source of truth for all admission decisions.

```
Academic Domain
    │
    ├── University Module
    │
    ├── Program Module
    │
    └── Admissions Module
        │
        ├── Eligibility Engine
        │
        ├── Academic Validation
        │
        ├── Policy Validation
        │
        ├── Admission Assessment
        │
        ├── Decision Engine
        │
        └── Future Domains
            │
            ├── Applications → uses Admissions Module
            ├── Scholarships → uses Admissions Module
            ├── AI Services → uses Admissions Module
            └── Financial → uses Admissions Module
```

## Decision

**EduBridge OS will adopt a Policy-Driven Admissions Architecture.**

### Core Principles

1. **Admission is Policy-Driven**
   - Admission decisions are based on policies
   - Policies are independently testable
   - Policies affect admission outcomes

2. **Eligibility is Central**
   - Every admission decision requires eligibility validation
   - Eligibility rules are independently configurable
   - Eligibility affects admission outcomes

3. **Academic Validation is Required**
   - Every admission decision requires academic validation
   - Academic validation is independently manageable
   - Academic validation affects admission outcomes

4. **Assessment is Tracked**
   - Every admission decision has an assessment record
   - Assessments are independently testable
   - Assessments affect admission outcomes

5. **Lifecycle is Managed**
   - Every admission decision has a lifecycle state
   - Lifecycle changes are logged
   - Lifecycle affects admission capabilities

### Architecture Diagram

```
                    Academic Domain
                           │
                    University Module
                           │
                    Program Module
                           │
                    Admissions Module
                           │
                    Eligibility Engine
                           │
                    Academic Validation
                           │
                    Policy Validation
                           │
                    Admission Assessment
                           │
                    Decision Engine
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
│                  uses Admissions Module                             │
│                           │                                         │
│                  APIs + Events                                      │
│                           │                                         │
│                  Admissions Module                                  │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### Admission Flow

```
Student
    │
Academic Profile
    │
Eligibility Engine
    │
Admission Requirements
    │
Program Validation
    │
University Validation
    │
Policy Validation
    │
Admission Assessment
    │
Admission Decision
    │
Future Domains
```

## Consequences

### Positive

1. **Single Source of Truth**
   - Admission decisions exist in exactly one place
   - No duplication, no inconsistency
   - Changes propagate automatically

2. **Centralized Validation**
   - All admission operations pass through validation
   - Eligibility is enforced
   - Policies are validated

3. **Simplified Integration**
   - Future domains consume through APIs
   - No cross-domain synchronization needed
   - Clean API boundaries

4. **Independent Testing**
   - Admissions Module is independently testable
   - Eligibility rules are verifiable
   - Policies are testable in isolation

5. **Security**
   - Admission operations are auditable
   - Access control is centralized
   - Changes are tracked

### Negative

1. **Single Point of Failure**
   - Admissions Module failure affects all domains
   - Mitigation: High availability, caching, redundancy

2. **Complex Initial Setup**
   - Requires careful design of admission entities
   - Mitigation: Clear standards, phased implementation

3. **Performance Overhead**
   - All domains must call Admissions APIs
   - Mitigation: Caching, read replicas, event-driven updates

## Compliance

### Required Standards

1. **Admission Requirements**
   - Every admission decision requires validated requirements
   - Requirements must be independently configurable
   - Requirements must be independently testable

2. **Eligibility Management**
   - Every admission decision requires eligibility validation
   - Eligibility rules must be independently configurable
   - Eligibility must affect admission outcomes

3. **Academic Validation**
   - Every admission decision requires academic validation
   - Academic validation must be independently manageable
   - Academic validation must affect admission outcomes

4. **Admission Policies**
   - Every admission decision requires policy validation
   - Policies must be independently testable
   - Policies must affect admission outcomes

5. **Admission Lifecycle**
   - Every admission decision must have a lifecycle state
   - Lifecycle changes must be logged
   - Lifecycle must affect admission capabilities

### Forbidden Patterns

1. **Duplicate Admission Records**
   - BAD: Multiple domains storing same admission decision
   - GOOD: Single Admissions Module as source of truth

2. **Cross-Domain Ownership**
   - BAD: Applications Domain making admission decisions
   - GOOD: Admission decisions belong to Admissions Module

3. **Eligibility Bypass**
   - BAD: Direct database updates without validation
   - GOOD: All operations through Admissions APIs

4. **Unauthorized Modifications**
   - BAD: Unauthenticated access to admission information
   - GOOD: Validated access through Admissions Module

## References

- ADR-005: Backend Architecture (Modular Monolith)
- ADR-006: Database Architecture (9 Schemas)
- ADR-012: Identity Domain as Guardian Domain
- ADR-018: Institution-Centric Academic Architecture
- ADR-019: Global Institution Management Architecture
- ADR-020: Global Academic Program Architecture
- ADMISSIONS_MANAGEMENT_STANDARDS.md
