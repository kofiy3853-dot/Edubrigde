# University Validation Framework Standards

LOCKED — Permanent — ADR-168

## Purpose

Establish University validation principle standards, validation layer standards, validation rule catalog standards, validation error model standards, validation responsibility standards, validation governance standards, and dependency rule standards for all future University validation developments in EduBridge OS.

## University Validation Foundation Architecture

```
Command
    |
    v
Structural Validation
    |
    v
Application Validation
    |
    v
Load Aggregate
    |
    v
Domain Validation
    |
    v
Policy Validation
    |
    v
Business Execution
```

**Note:** Cross-cutting concerns are shared across all modules via repository-wide services. This module owns ONLY its unique responsibilities.

## Validation Principles

### Permanent Ownership
University Validation Framework Module owns all validation principle standards.

### Required Validation Principles
- Every validation rule MUST be deterministic
- Every validation rule MUST be business-oriented
- Every validation rule MUST be independently testable
- Every validation rule MUST be technology-independent
- Every validation rule MUST be explicit
- Every validation rule MUST be reusable where applicable

### Principle Requirements
- All principles MUST be documented
- All principles MUST be enforced
- All principles MUST be validated
- All principles MUST be auditable
- All principle history MUST be maintained

---

## 1. Validation Layer Standards

### Permanent Ownership
University Validation Framework Module owns all validation layer standards.

### Approved Validation Layers

#### Layer 1 — Structural Validation
Purpose: Verify command and query structure.
Examples: Required fields, Identifier format, Data type validation, Maximum field length, Enumeration values
Performed before application processing.

#### Layer 2 — Application Validation
Purpose: Verify application-level conditions.
Examples: Resource existence, Permission availability, Aggregate version checks, Duplicate request detection
Performed by the Application Layer.

#### Layer 3 — Domain Validation
Purpose: Protect business invariants.
Examples: University name uniqueness (through domain policies), Valid publication state transitions, Program ownership, Campus consistency, Tuition validity, Accreditation rules
Performed by Aggregates and Domain Services.

#### Layer 4 — Policy Validation
Purpose: Evaluate organizational and business policies.
Examples: Publication eligibility, Accreditation compliance, Intake scheduling windows, Administrative approval requirements
Policies remain external to entities but within the domain.

### Validation Layer Requirements
- All layers MUST be documented
- All layers MUST be versioned
- All layer changes MUST be approved
- All layer changes MUST be auditable
- All layer history MUST be maintained
- Each validation layer executes in sequence

---

## 2. Validation Rule Catalog Standards

### Permanent Ownership
University Validation Framework Module owns all validation rule catalog standards.

### Required Validation Rule Catalog Rules
- All rules MUST be documented
- All rules MUST be versioned
- All rule changes MUST be approved
- All rule changes MUST be auditable
- All rule history MUST be maintained

### Approved Validation Rule Categories

#### University Rules
- Official name required
- Institution identifier required
- Country required
- At least one contact method
- Publication requires verification

#### Campus Rules
- Campus name required
- Valid address required
- Unique campus identifier
- Campus belongs to university

#### Academic Program Rules
- Program name required
- Degree level required
- Faculty association required
- Valid study mode

#### Tuition Rules
- Currency required
- Amount must be non-negative
- Effective period required

#### Admission Rules
- Required documents defined
- Qualification criteria defined
- Intake association valid

#### Accreditation Rules
- Accrediting authority required
- Validity period required
- Status must be recognized

### Validation Rule Catalog Requirements
- Each rule MUST have clear semantics
- Each rule MUST be independently testable
- Each rule MUST use ubiquitous language
- Each rule MUST be technology-independent
- Each rule MUST execute at the correct layer

---

## 3. Validation Error Model Standards

### Permanent Ownership
University Validation Framework Module owns all validation error model standards.

### Required Validation Error Fields
- Error Code
- Error Category
- Human-readable Message
- Field (if applicable)
- Correlation Identifier
- Timestamp

### Validation Error Model Rules
- Validation errors are immutable
- Validation errors are consistent across the bounded context
- All errors MUST be documented
- All errors MUST be versioned
- All error changes MUST be approved
- All error changes MUST be auditable
- All error history MUST be maintained

---

## 4. Validation Responsibility Standards

### Permanent Ownership
University Validation Framework Module owns all validation responsibility standards.

### Required Validation Responsibilities

#### Commands
Responsible for: Structural validity only

#### Application Services
Responsible for: Resource existence, Authorization prerequisites, Transaction preparation

#### Aggregates
Responsible for: Business invariants, State transitions, Aggregate consistency

#### Domain Services
Responsible for: Cross-aggregate rules, Complex business calculations, Policy coordination

#### Policies
Responsible for: Organizational rules, Compliance requirements, Publication eligibility, Administrative constraints

### Validation Responsibility Requirements
- All responsibilities MUST be documented
- All responsibilities MUST be enforced
- All responsibilities MUST be validated
- All responsibilities MUST be auditable
- All responsibility history MUST be maintained
- Business rules must never be duplicated across layers

---

## 5. Validation Governance Standards

### Permanent Ownership
University Validation Framework Module owns all validation governance standards.

### Required Governance Rules
- Every validation rule MUST have a single owner
- Every validation rule MUST execute at the correct layer
- Every validation rule MUST be independently testable
- Every validation rule MUST avoid duplication
- Every validation rule MUST use ubiquitous language
- Business rules must never be duplicated across layers

### Governance Requirements
- All governance rules MUST be documented
- All governance rules MUST be enforced
- All governance rules MUST be validated
- All governance rules MUST be auditable
- All governance rule history MUST be maintained

---

## 6. Dependency Rules Standards

### Permanent Ownership
University Validation Framework Module owns all dependency rules standards.

### Required Dependency Rules
- Command MAY trigger Application Service
- Application Service MAY invoke Aggregate
- Aggregate MAY apply Domain Policy
- Application Service MAY return Validation Result
- UI MUST NOT own business validation logic
- Database MUST NOT own business rule ownership
- Infrastructure MUST NOT own domain validation
- Business validation remains within the Domain and Application Layers

### Allowed Dependencies
```
Command
    |
    v
Application Service
    |
    v
Aggregate
    |
    v
Domain Policy

Application Service
    |
    v
Validation Result
```

### Prohibited Dependencies
```
UI
 |
 v
Business Validation

Database
 |
 v
Business Rule Ownership

Infrastructure
 |
 v
Domain Validation
```

### Dependency Requirements
- All dependencies MUST be documented
- All dependencies MUST be versioned
- All dependency changes MUST be approved
- All dependency changes MUST be auditable
- All dependency history MUST be maintained

---

## 7. Implementation Rules Standards

### Permanent Ownership
University Validation Framework Module owns all implementation rules standards.

### Required Implementation Rules
1. Validation is layered
2. Structural validation occurs first
3. Business invariants belong to Aggregates
4. Organizational rules belong to Policies
5. Validation errors are immutable
6. Validation logic is technology-independent
7. Rules are independently testable
8. Business rule duplication is prohibited
9. Validation uses ubiquitous language
10. Domain integrity is preserved

### Implementation Requirements
- All implementation rules MUST be documented
- All implementation rules MUST be enforced
- All implementation rules MUST be validated
- All implementation rules MUST be auditable
- All implementation rule history MUST be maintained
