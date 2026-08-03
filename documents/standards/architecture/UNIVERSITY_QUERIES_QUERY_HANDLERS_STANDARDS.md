# University Queries & Query Handlers Standards

LOCKED — Permanent — ADR-164

## Purpose

Establish University query principle standards, ownership standards, catalog standards, handler catalog standards, execution flow standards, read-side responsibility standards, projection usage standards, query validation standards, governance standards, and dependency rule standards for all future University query and handler developments in EduBridge OS.

## University Query Foundation Architecture

```
Query (Immutable Request)
        |
    Query Handler (Read Coordination)
        |
    Read Repository (Data Access)
        |
    Projection (Optimized View)
        |
    Read Model (Result)
```

**Note:** Cross-cutting concerns are shared across all modules via repository-wide services. This module owns ONLY its unique responsibilities.

## Query Principles

### Permanent Ownership
University Queries & Query Handlers Module owns all query principle standards.

### Required Query Principles
- Every Query MUST be read-only
- Every Query MUST be immutable
- Every Query MUST be technology-independent
- Every Query MUST be independently testable
- Every Query MUST be business-oriented
- Every Query MUST be side-effect free

### Principle Requirements
- All principles MUST be documented
- All principles MUST be enforced
- All principles MUST be validated
- All principles MUST be auditable
- All principle history MUST be maintained

---

## 1. Query Ownership Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all query ownership standards.

### Required Query Ownership Rules
- University bounded context owns queries related to university discovery
- University bounded context owns queries related to university profiles
- University bounded context owns queries related to academic programs
- University bounded context owns queries related to faculties
- University bounded context owns queries related to campuses
- University bounded context owns queries related to admission requirements
- University bounded context owns queries related to tuition structures
- University bounded context owns queries related to intake periods
- University bounded context owns queries related to accreditation
- University bounded context owns queries related to rankings
- Cross-domain queries remain the responsibility of their owning bounded contexts

### Query Ownership Requirements
- All ownership MUST be documented
- All ownership MUST be enforced
- All ownership MUST be validated
- All ownership MUST be auditable
- All ownership history MUST be maintained

---

## 2. Query Catalog Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all query catalog standards.

### Required Catalog Rules
- All queries MUST be documented
- All queries MUST be versioned
- All query changes MUST be approved
- All query changes MUST be auditable
- All query history MUST be maintained

### Approved Query Categories

#### GetUniversityByIdQuery
Purpose: Retrieve the complete read model for a specific university.
Parameters: UniversityId

#### SearchUniversitiesQuery
Purpose: Search universities using business criteria.
Supported filters: Name, Country, Degree level, Accreditation, Ranking, Tuition range, Study mode

#### GetUniversityProfileQuery
Purpose: Retrieve public university profile information.
Parameters: UniversityId

#### GetAcademicProgramsQuery
Purpose: Retrieve academic programs offered by a university.
Parameters: UniversityId
Optional filters: Degree level, Faculty, Study mode

#### GetCampusDirectoryQuery
Purpose: Retrieve all campuses for a university.
Parameters: UniversityId

#### GetAdmissionRequirementsQuery
Purpose: Retrieve admission requirements for one or more academic programs.
Parameters: UniversityId, ProgramId (optional)

#### GetTuitionInformationQuery
Purpose: Retrieve tuition and fee information.
Parameters: UniversityId, Academic Program (optional)

#### GetIntakeScheduleQuery
Purpose: Retrieve active and upcoming intake periods.
Parameters: UniversityId

#### GetAccreditationQuery
Purpose: Retrieve accreditation information.
Parameters: UniversityId

#### CompareUniversitiesQuery
Purpose: Retrieve comparable read models for multiple universities.
Parameters: UniversityId collection

### Catalog Requirements
- Each query MUST have clear semantics
- Each query MUST be immutable
- Each query MUST be independently testable
- Each query MUST be documented
- Each query MUST use ubiquitous language

---

## 3. Query Handler Catalog Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all query handler catalog standards.

### Required Handler Catalog Rules
- Each query has exactly one handler
- A query must never have multiple handlers
- All handlers MUST be documented
- All handlers MUST be versioned
- All handler changes MUST be approved

### Approved Query Handler Categories
- GetUniversityByIdQueryHandler
- SearchUniversitiesQueryHandler
- GetUniversityProfileQueryHandler
- GetAcademicProgramsQueryHandler
- GetCampusDirectoryQueryHandler
- GetAdmissionRequirementsQueryHandler
- GetTuitionInformationQueryHandler
- GetIntakeScheduleQueryHandler
- GetAccreditationQueryHandler
- CompareUniversitiesQueryHandler

### Handler Catalog Requirements
- Each handler MUST have clear semantics
- Each handler MUST be stateless
- Each handler MUST be independently testable
- Each handler MUST be documented
- Each handler MUST use ubiquitous language

---

## 4. Query Execution Flow Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all query execution flow standards.

### Required Execution Flow Stages
1. Query
2. Structural Validation
3. Query Handler
4. Read Repository
5. Projection
6. Read Model
7. Result

### Execution Flow Rules
- The read-side never loads or modifies the University Aggregate
- All execution flow stages are documented
- All execution flow stages are auditable
- All execution flow history is maintained

### Execution Flow Requirements
- All execution flows MUST be documented
- All execution flows MUST be versioned
- All execution flow changes MUST be approved
- All execution flow changes MUST be auditable
- All execution flow history MUST be maintained

---

## 5. Read-Side Responsibility Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all read-side responsibility standards.

### Required Read-Side Responsibilities
- Validating query structure
- Coordinating read repositories
- Retrieving projections
- Applying pagination
- Applying sorting
- Applying filtering
- Returning read models

### Read-Side Restrictions
- Business decisions remain outside the read-side
- Query Handlers do not modify state
- Query Handlers do not execute business logic
- Query Handlers do not access write repositories

### Read-Side Requirements
- All read-side responsibilities MUST be documented
- All read-side responsibilities MUST be enforced
- All read-side responsibilities MUST be validated
- All read-side responsibilities MUST be auditable
- All read-side responsibility history MUST be maintained

---

## 6. Projection Usage Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all projection usage standards.

### Required Projection Usage Rules
- Query Handlers consume projections generated from University Domain Events
- Read models are optimized for query performance
- Projections are read-only
- Projections are documented
- Projection changes are approved

### Approved Projections
- University Summary
- University Profile
- Program Catalog
- Campus Directory
- Tuition Catalog
- Intake Calendar
- Accreditation Overview
- University Comparison View

### Projection Usage Requirements
- All projection usage MUST be documented
- All projection usage MUST be versioned
- All projection usage changes MUST be approved
- All projection usage changes MUST be auditable
- All projection usage history MUST be maintained

---

## 7. Query Validation Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all query validation standards.

### Required Query Validation Rules
- Required parameter checks
- Identifier format validation
- Pagination validation
- Sorting validation
- Filter validation
- Business rule evaluation is not performed during query execution

### Query Validation Requirements
- All query validation MUST be documented
- All query validation MUST be enforced
- All query validation MUST be validated
- All query validation MUST be auditable
- All query validation history MUST be maintained

---

## 8. Governance Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all governance standards.

### Required Governance Rules
- Every Query MUST represent one information request
- Every Query MUST be immutable
- Every Query MUST use ubiquitous language
- Every Query MUST be independently testable
- Every Query MUST remain read-only
- Every Query Handler MUST handle exactly one query
- Every Query Handler MUST remain stateless
- Every Query Handler MUST avoid business rule implementation
- Every Query Handler MUST return read-optimized models

### Governance Requirements
- All governance rules MUST be documented
- All governance rules MUST be enforced
- All governance rules MUST be validated
- All governance rules MUST be auditable
- All governance rule history MUST be maintained

---

## 9. Dependency Rules Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all dependency rules standards.

### Required Dependency Rules
- Query MAY depend on Query Handler
- Query Handler MAY depend on Read Repository
- Read Repository MAY depend on Projection
- Query MUST NOT depend on University Aggregate
- Query Handler MUST NOT depend on Command Handler
- Query Handler MUST NOT depend on Business Rule Implementation
- Query Handler MUST NOT depend on Write Repository
- The read-side remains isolated from the write-side

### Allowed Dependencies
```
Query
    |
    v
Query Handler
    |
    v
Read Repository
    |
    v
Projection
```

### Prohibited Dependencies
```
Query
    |
    v
University Aggregate

Query Handler
    |
    v
Command Handler

Query Handler
    |
    v
Business Rule Implementation

Query Handler
    |
    v
Write Repository
```

### Dependency Requirements
- All dependencies MUST be documented
- All dependencies MUST be versioned
- All dependency changes MUST be approved
- All dependency changes MUST be auditable
- All dependency history MUST be maintained

---

## 10. Implementation Rules Standards

### Permanent Ownership
University Queries & Query Handlers Module owns all implementation rules standards.

### Required Implementation Rules
1. Queries are read-only
2. Queries are immutable
3. One query maps to one handler
4. Query Handlers are stateless
5. Query Handlers never modify state
6. Read models are projection-based
7. Business rules remain in the domain
8. Queries are technology-independent
9. CQRS read/write separation is preserved
10. Read-side performance is prioritized

### Implementation Requirements
- All implementation rules MUST be documented
- All implementation rules MUST be enforced
- All implementation rules MUST be validated
- All implementation rules MUST be auditable
- All implementation rule history MUST be maintained
