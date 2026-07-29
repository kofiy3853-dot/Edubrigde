# ADR-023: Student-Centric Financial Intelligence Architecture

## Status
LOCKED — Permanent Architecture Decision

## Date
July 29, 2026

## Context

EduBridge OS requires a Financial Domain that serves as the permanent owner of educational financial intelligence. Financial planning is not merely tuition calculation—every financial decision must be driven by a student's academic pathway, destination country, university requirements, living expenses, scholarship opportunities, and long-term affordability assessments.

### The Problem

Without centralized financial intelligence, the following risks emerge:

```
RISK 1: Scholarship Domain → owns financial planning (SCATTERED)
RISK 2: AI Services → owns cost calculations (DUPLICATE)
RISK 3: Applications Domain → owns affordability assessments (CROSS-DOMAIN)
RISK 4: Learning Domain → owns budget planning (INCONSISTENT)
```

These patterns lead to:
- **Data duplication** — Same financial information scattered across domains
- **Inconsistency** — Different domains have different financial outcomes
- **Maintenance nightmare** — Changes require updates in multiple places
- **Integration complexity** — Every domain must sync with every other domain
- **Validation bypass** — No centralized validation of financial information

### The Solution

Student-Centric Financial Intelligence Architecture establishes the Financial Domain as the permanent owner of educational financial intelligence.

```
Financial Domain (Permanent Financial Authority)
    │
    ├── Financial Planning
    │
    ├── Cost Management
    │
    ├── Country Cost Standards
    │
    ├── Financial Policies
    │
    ├── Validation Layer
    │
    ├── Audit Layer
    │
    └── Future Domains
        │
        ├── Scholarships → uses Financial Domain
        ├── Budgeting → uses Financial Domain
        ├── AI Services → uses Financial Domain
        └── Learning → uses Financial Domain
```

## Decision

**EduBridge OS will adopt a Student-Centric Financial Intelligence Architecture.**

### Core Principles

1. **Student-Centric**
   - Every financial decision is driven by student's academic pathway
   - Every financial decision considers destination country
   - Every financial decision considers university requirements
   - Every financial decision considers living expenses

2. **Intelligence-Driven**
   - Financial planning includes affordability assessments
   - Financial planning includes risk level analysis
   - Financial planning includes scholarship opportunity identification
   - Financial planning includes long-term financial sustainability

3. **Country-Aware**
   - Financial planning considers country-specific costs
   - Financial planning considers currency exchange rates
   - Financial planning considers country-specific regulations
   - Financial planning considers country-specific financial policies

4. **Policy-Driven**
   - Every financial operation complies with financial policies
   - Policies are independently configurable
   - Policy violations are logged and auditable

5. **Audit-Comprehensive**
   - Every financial operation is auditable
   - Audit records are immutable
   - Audit supports monitoring and compliance

### Architecture Diagram

```
                    Financial Domain (Permanent Financial Authority)
                           │
                    ┌──────┴──────┐
                    │             │
              Financial Planning   Cost Management
                    │             │
              Country Cost Standards   Financial Policies
                    │             │
                    └──────┬──────┘
                           │
                    Validation Layer
                           │
                    Audit Layer
                           │
                    Future Domains

┌─────────────────────────────────────────────────────────────────────┐
│                                                                     │
│  Scholarships    Budgeting    AI Services    Learning Services      │
│       │               │               │               │             │
│       └───────────────┴───────────────┴───────────────┘             │
│                           │                                         │
│                  uses Financial Domain                              │
│                           │                                         │
│                  APIs + Events                                      │
│                           │                                         │
│                  Financial Domain                                   │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

## Consequences

### Positive

1. **Single Source of Truth**
   - Financial information exists in exactly one place
   - No duplication, no inconsistency
   - Changes propagate automatically

2. **Centralized Validation**
   - All financial operations pass through validation
   - Validation is independently testable
   - Validation supports policy-driven rules

3. **Simplified Integration**
   - Future domains consume through APIs
   - No cross-domain synchronization needed
   - Clean API boundaries

4. **Independent Testing**
   - Financial Domain is independently testable
   - Validation rules are verifiable
   - Policies are testable in isolation

5. **Security**
   - Financial operations are auditable
   - Access control is centralized
   - Changes are tracked

6. **Compliance**
   - Audit records support compliance
   - Monitoring supports operational visibility
   - Policies support regulatory requirements

### Negative

1. **Single Point of Failure**
   - Financial Domain failure affects all domains
   - Mitigation: High availability, caching, redundancy

2. **Complex Initial Setup**
   - Requires careful design of financial entities
   - Mitigation: Clear standards, phased implementation

3. **Performance Overhead**
   - All domains must call Financial APIs
   - Mitigation: Caching, read replicas, event-driven updates

## Compliance

### Required Standards

1. **Student-Centric**
   - Every financial decision is driven by student's academic pathway
   - Every financial decision considers destination country
   - Every financial decision considers university requirements
   - Every financial decision considers living expenses

2. **Intelligence-Driven**
   - Financial planning includes affordability assessments
   - Financial planning includes risk level analysis
   - Financial planning includes scholarship opportunity identification
   - Financial planning includes long-term financial sustainability

3. **Country-Aware**
   - Financial planning considers country-specific costs
   - Financial planning considers currency exchange rates
   - Financial planning considers country-specific regulations
   - Financial planning considers country-specific financial policies

4. **Policy-Driven**
   - Every financial operation complies with financial policies
   - Policies are independently configurable
   - Policy violations are logged and auditable

5. **Audit-Comprehensive**
   - Every financial operation is auditable
   - Audit records are immutable
   - Audit supports monitoring and compliance

### Forbidden Patterns

1. **Duplicate Financial Records**
   - BAD: Multiple domains storing same financial information
   - GOOD: Single Financial Domain as source of truth

2. **Cross-Domain Ownership**
   - BAD: Scholarship Domain managing financial planning
   - GOOD: Financial planning belongs to Financial Domain

3. **Validation Bypass**
   - BAD: Direct database updates without validation
   - GOOD: All operations through Financial APIs

4. **Policy Bypass**
   - BAD: Operations without policy compliance
   - GOOD: All operations comply with financial policies

5. **Audit Bypass**
   - BAD: Operations without audit logging
   - GOOD: All operations generate audit records

## References

- ADR-005: Backend Architecture (Modular Monolith)
- ADR-006: Database Architecture (9 Schemas)
- ADR-012: Identity Domain as Guardian Domain
- ADR-018: Institution-Centric Academic Architecture
- ADR-019: Global Institution Management Architecture
- ADR-020: Global Academic Program Architecture
- ADR-021: Policy-Driven Admissions Architecture
- ADR-022: Academic-First Repository Architecture
- FINANCIAL_DOMAIN_STANDARDS.md
