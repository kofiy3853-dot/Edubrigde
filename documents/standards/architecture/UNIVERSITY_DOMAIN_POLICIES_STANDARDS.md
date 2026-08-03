# University Domain Policies Standards

LOCKED — Permanent — ADR-158

## Purpose

Establish University domain policy principle standards, ownership standards, catalog standards, policy architecture standards, policy outcome standards, policy responsibility standards, policy collaboration standards, governance standards, and dependency rule standards for all future University domain policy developments in EduBridge OS.

## University Domain Policy Foundation Architecture

```
Application Service / Domain Service / Aggregate
        |
    Domain Policy (Business Decision Logic)
        |
    Business Decision (Approved / Rejected / Requires Review)
```

**Note:** Cross-cutting concerns are shared across all modules via repository-wide services. This module owns ONLY its unique responsibilities.

## Domain Policy Principles

### Permanent Ownership
University Domain Policies Module owns all domain policy principle standards.

### Required Domain Policy Principles
- Every Domain Policy MUST be stateless
- Every Domain Policy MUST be deterministic
- Every Domain Policy MUST be business-oriented
- Every Domain Policy MUST be independently testable
- Every Domain Policy MUST be technology-independent
- Every Domain Policy MUST be reusable

### Principle Requirements
- All principles MUST be documented
- All principles MUST be enforced
- All principles MUST be validated
- All principles MUST be auditable
- All principle history MUST be maintained

---

## 1. Policy Ownership Standards

### Permanent Ownership
University Domain Policies Module owns all policy ownership standards.

### Required Policy Ownership Rules
- University bounded context owns policies related to university publication
- University bounded context owns policies related to institutional eligibility
- University bounded context owns policies related to program availability
- University bounded context owns policies related to admission requirements
- University bounded context owns policies related to accreditation validity
- University bounded context owns policies related to tuition consistency
- University bounded context owns policies related to campus operational status
- University bounded context owns policies related to intake scheduling
- Policies belonging to other bounded contexts remain external

### Policy Ownership Requirements
- All ownership MUST be documented
- All ownership MUST be enforced
- All ownership MUST be validated
- All ownership MUST be auditable
- All ownership history MUST be maintained

---

## 2. Domain Policy Catalog Standards

### Permanent Ownership
University Domain Policies Module owns all domain policy catalog standards.

### Required Catalog Rules
- All domain policies MUST be documented
- All domain policies MUST be versioned
- All domain policy changes MUST be approved
- All domain policy changes MUST be auditable
- All domain policy history MUST be maintained

### Approved Domain Policy Categories

#### UniversityPublicationPolicy
Purpose: Determines whether a university is eligible to be published.
Evaluates: Profile completeness, accreditation status, program availability, required contact information, mandatory institutional metadata

#### AdmissionEligibilityPolicy
Purpose: Determines whether an academic program has complete admission requirements.
Evaluates: Entry qualifications, required documentation, language requirements, mandatory criteria

#### AccreditationValidityPolicy
Purpose: Determines whether accreditation information is valid for business use.
Evaluates: Accreditation status, validity period, accrediting authority, scope of accreditation

#### TuitionConsistencyPolicy
Purpose: Ensures tuition structures remain internally consistent.
Evaluates: Currency consistency, fee completeness, tuition categories, business constraints

#### CampusOperationalPolicy
Purpose: Determines whether a campus may participate in university operations.
Evaluates: Operational status, campus availability, business readiness

#### IntakeSchedulingPolicy
Purpose: Prevents invalid intake schedules.
Evaluates: Date ordering, schedule overlap, academic calendar consistency

#### ProgramAvailabilityPolicy
Purpose: Determines whether an academic program can be offered.
Evaluates: Program lifecycle state, faculty ownership, admission readiness, accreditation requirements

#### UniversityArchivalPolicy
Purpose: Determines whether a university may transition to the Archived lifecycle state.
Evaluates: Active programs, outstanding obligations, business prerequisites

### Catalog Requirements
- Each domain policy MUST have clear semantics
- Each domain policy MUST be stateless
- Each domain policy MUST be deterministic
- Each domain policy MUST be independently testable
- Each domain policy MUST be documented
- Each domain policy MUST use ubiquitous language

---

## 3. Policy Architecture Standards

### Permanent Ownership
University Domain Policies Module owns all policy architecture standards.

### Required Policy Architecture Rules
- Policies return business outcomes without mutating domain state
- Policies evaluate business rules
- Policies assess eligibility
- Policies determine readiness
- Policies validate business conditions
- Policies produce business decisions

### Policy Architecture Requirements
- All policy architecture MUST be documented
- All policy architecture MUST be versioned
- All policy architecture changes MUST be approved
- All policy architecture changes MUST be auditable
- All policy architecture history MUST be maintained

---

## 4. Policy Outcome Standards

### Permanent Ownership
University Domain Policies Module owns all policy outcome standards.

### Approved Policy Outcomes
- Approved
- Rejected
- Requires Review
- Not Applicable

### Policy Outcome Rules
- The consuming service or aggregate determines how to act on the outcome
- Policy outcomes are documented
- Policy outcome changes are approved
- Policy outcome history is maintained

### Policy Outcome Requirements
- All policy outcomes MUST be documented
- All policy outcomes MUST be versioned
- All policy outcome changes MUST be approved
- All policy outcome changes MUST be auditable
- All policy outcome history MUST be maintained

---

## 5. Policy Responsibility Standards

### Permanent Ownership
University Domain Policies Module owns all policy responsibility standards.

### Required Policy Responsibilities
- Evaluate business rules
- Assess eligibility
- Determine readiness
- Validate business conditions
- Produce business decisions

### Policy Restrictions
- Policies MUST NOT modify aggregates
- Policies MUST NOT persist data
- Policies MUST NOT publish events
- Policies MUST NOT access infrastructure
- Policies MUST NOT perform presentation logic

### Policy Responsibility Requirements
- All policy responsibilities MUST be documented
- All policy responsibilities MUST be enforced
- All policy responsibilities MUST be validated
- All policy responsibilities MUST be auditable
- All policy responsibility history MUST be maintained

---

## 6. Policy Collaboration Standards

### Permanent Ownership
University Domain Policies Module owns all policy collaboration standards.

### Required Policy Collaboration Rules
- Policies may evaluate information from University Aggregate
- Policies may evaluate information from University Entities
- Policies may evaluate information from University Value Objects
- Policies may be invoked by Domain Services
- Policies may be invoked by Application Services
- Policies may be invoked by Aggregate methods (where appropriate)

### Policy Collaboration Requirements
- All policy collaboration MUST be documented
- All policy collaboration MUST be versioned
- All policy collaboration changes MUST be approved
- All policy collaboration changes MUST be auditable
- All policy collaboration history MUST be maintained

---

## 7. Governance Standards

### Permanent Ownership
University Domain Policies Module owns all governance standards.

### Required Governance Rules
- Every Domain Policy MUST represent a single business decision
- Every Domain Policy MUST use ubiquitous language
- Every Domain Policy MUST remain deterministic
- Every Domain Policy MUST be independently testable
- Every Domain Policy MUST avoid duplication
- Every Domain Policy MUST remain free of infrastructure dependencies
- Business decision logic should not be duplicated across services or aggregates

### Governance Requirements
- All governance rules MUST be documented
- All governance rules MUST be enforced
- All governance rules MUST be validated
- All governance rules MUST be auditable
- All governance rule history MUST be maintained

---

## 8. Dependency Rules Standards

### Permanent Ownership
University Domain Policies Module owns all dependency rules standards.

### Required Dependency Rules
- Domain Services MAY depend on Domain Policies
- Aggregates MAY depend on Domain Policies
- Domain Policies MUST NOT depend on Database
- Domain Policies MUST NOT depend on External API
- Domain Policies MUST NOT depend on Messaging Infrastructure
- Domain Policies MUST NOT depend on Presentation Layer
- Policies remain isolated from infrastructure and transport mechanisms

### Allowed Dependencies
```
Domain Service
        |
        v
Domain Policy

Aggregate
        |
        v
Domain Policy
```

### Prohibited Dependencies
```
Domain Policy
        |
        v
Database

Domain Policy
        |
        v
External API

Domain Policy
        |
        v
Messaging Infrastructure

Domain Policy
        |
        v
Presentation Layer
```

### Dependency Requirements
- All dependencies MUST be documented
- All dependencies MUST be versioned
- All dependency changes MUST be approved
- All dependency changes MUST be auditable
- All dependency history MUST be maintained

---

## 9. Implementation Rules Standards

### Permanent Ownership
University Domain Policies Module owns all implementation rules standards.

### Required Implementation Rules
1. Policies are stateless
2. Policies return business decisions only
3. Policies never modify domain state
4. Infrastructure dependencies are prohibited
5. Policies use ubiquitous language
6. Business rules are centralized
7. Policies are independently testable
8. Policy duplication is prohibited
9. Domain integrity is preserved
10. Policy outcomes are consumed by aggregates or services

### Implementation Requirements
- All implementation rules MUST be documented
- All implementation rules MUST be enforced
- All implementation rules MUST be validated
- All implementation rules MUST be auditable
- All implementation rule history MUST be maintained
