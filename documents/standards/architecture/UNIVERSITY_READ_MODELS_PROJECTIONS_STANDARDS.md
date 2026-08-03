# University Read Models & Projections Standards

LOCKED — Permanent — ADR-165

## Purpose

Establish University read model principle standards, projection principle standards, read model catalog standards, projection catalog standards, event-to-projection mapping standards, projection lifecycle standards, synchronization strategy standards, governance standards, and dependency rule standards for all future University read model and projection developments in EduBridge OS.

## University Read Model & Projection Foundation Architecture

```
Domain Event
    |
    v
Projection (Event Consumer)
    |
    v
Read Model (Query-Optimized)
    |
    v
Query Handler (Service Layer)
    |
    v
Client
```

**Note:** Cross-cutting concerns are shared across all modules via repository-wide services. This module owns ONLY its unique responsibilities.

## Read Model Principles

### Permanent Ownership
University Read Models & Projections Module owns all read model principle standards.

### Required Read Model Principles
- Every Read Model MUST be query-optimized
- Every Read Model MUST be read-only
- Every Read Model MUST be technology-independent
- Every Read Model MUST be independently testable
- Every Read Model MUST be derived from Domain Events
- Every Read Model MUST be disposable and rebuildable

### Principle Requirements
- All principles MUST be documented
- All principles MUST be enforced
- All principles MUST be validated
- All principles MUST be auditable
- All principle history MUST be maintained

---

## 1. Projection Principles Standards

### Permanent Ownership
University Read Models & Projections Module owns all projection principle standards.

### Required Projection Principles
- Every Projection MUST be event-driven
- Every Projection MUST be deterministic
- Every Projection MUST be idempotent
- Every Projection MUST be independently testable
- Every Projection MUST be technology-independent
- Every Projection MUST be replayable

### Projection Principle Requirements
- All projection principles MUST be documented
- All projection principles MUST be enforced
- All projection principles MUST be validated
- All projection principles MUST be auditable
- All projection principle history MUST be maintained

---

## 2. Read Model Catalog Standards

### Permanent Ownership
University Read Models & Projections Module owns all read model catalog standards.

### Required Read Model Catalog Rules
- All read models MUST be documented
- All read models MUST be versioned
- All read model changes MUST be approved
- All read model changes MUST be auditable
- All read model history MUST be maintained

### Approved Read Model Categories

#### UniversitySummaryReadModel
Purpose: Provides lightweight information for search results.
Contains: UniversityId, Official Name, Country, Accreditation Status, Ranking Summary, Study Modes

#### UniversityProfileReadModel
Purpose: Provides comprehensive institutional information.
Contains: University Profile, Contact Information, Campuses, Faculties, Accreditation, Rankings

#### AcademicProgramCatalogReadModel
Purpose: Provides searchable academic program information.
Contains: Program List, Degree Level, Faculty, Duration, Study Mode

#### CampusDirectoryReadModel
Purpose: Provides campus information.
Contains: Campus Details, Address, Operational Status, Contact Information

#### TuitionCatalogReadModel
Purpose: Provides tuition and fee information.
Contains: Tuition Categories, Fee Structure, Currency, Effective Dates

#### AdmissionRequirementsReadModel
Purpose: Provides admission information.
Contains: Program Requirements, Qualification Criteria, Required Documents

#### IntakeCalendarReadModel
Purpose: Provides application and enrollment schedules.
Contains: Intake Periods, Opening Dates, Closing Dates, Enrollment Dates

#### AccreditationOverviewReadModel
Purpose: Provides accreditation information.
Contains: Accreditation Authority, Accreditation Status, Validity Period

#### UniversityComparisonReadModel
Purpose: Supports side-by-side university comparison.
Contains: Academic Programs, Tuition, Rankings, Accreditation, Study Modes

### Read Model Catalog Requirements
- Each read model MUST serve one query purpose
- Each read model MUST be query-optimized
- Each read model MUST be read-only
- Each read model MUST be rebuildable
- Each read model MUST avoid business logic
- Each read model MUST use ubiquitous language

---

## 3. Projection Catalog Standards

### Permanent Ownership
University Read Models & Projections Module owns all projection catalog standards.

### Required Projection Catalog Rules
- All projections MUST be documented
- All projections MUST be versioned
- All projection changes MUST be approved
- All projection changes MUST be auditable
- All projection history MUST be maintained

### Approved Projection Categories
- UniversitySummaryProjection
- UniversityProfileProjection
- AcademicProgramProjection
- CampusProjection
- TuitionProjection
- AdmissionRequirementsProjection
- IntakeCalendarProjection
- AccreditationProjection
- UniversityComparisonProjection

### Projection Catalog Requirements
- Each projection MUST be deterministic
- Each projection MUST be idempotent
- Each projection MUST consume immutable Domain Events
- Each projection MUST preserve event ordering where required
- Each projection MUST be independently testable
- Each projection MUST use ubiquitous language

---

## 4. Event-to-Projection Mapping Standards

### Permanent Ownership
University Read Models & Projections Module owns all event-to-projection mapping standards.

### Required Event-to-Projection Mappings
- UniversityRegistered -> UniversitySummaryProjection
- UniversityPublished -> UniversityProfileProjection
- AcademicProgramAdded -> AcademicProgramProjection
- CampusAdded -> CampusProjection
- TuitionStructureUpdated -> TuitionProjection
- AdmissionRequirementsUpdated -> AdmissionRequirementsProjection
- IntakePeriodScheduled -> IntakeCalendarProjection
- AccreditationUpdated -> AccreditationProjection

### Mapping Requirements
- All mappings MUST be documented
- All mappings MUST be versioned
- All mapping changes MUST be approved
- All mapping changes MUST be auditable
- All mapping history MUST be maintained
- Each Domain Event is mapped to one or more projections based on business requirements

---

## 5. Projection Lifecycle Standards

### Permanent Ownership
University Read Models & Projections Module owns all projection lifecycle standards.

### Required Projection Lifecycle Stages
1. Domain Event
2. Projection Receives Event
3. Read Model Updated
4. Query Available

### Projection Lifecycle Rules
- Projection processing does not modify the write model
- All lifecycle stages are documented
- All lifecycle stages are auditable
- All lifecycle history is maintained

### Projection Lifecycle Requirements
- All lifecycle stages MUST be documented
- All lifecycle stages MUST be versioned
- All lifecycle stage changes MUST be approved
- All lifecycle stage changes MUST be auditable
- All lifecycle stage history MUST be maintained

---

## 6. Synchronization Strategy Standards

### Permanent Ownership
University Read Models & Projections Module owns all synchronization strategy standards.

### Required Synchronization Characteristics
- Eventually consistent
- Replayable
- Ordered within an aggregate
- Idempotent
- Recoverable after interruption

### Synchronization Rules
- Temporary differences between read and write models are acceptable within defined consistency boundaries
- All synchronization characteristics are documented
- All synchronization characteristics are auditable
- All synchronization history is maintained

### Synchronization Requirements
- All synchronization strategies MUST be documented
- All synchronization strategies MUST be versioned
- All synchronization strategy changes MUST be approved
- All synchronization strategy changes MUST be auditable
- All synchronization strategy history MUST be maintained

---

## 7. Projection Responsibilities Standards

### Permanent Ownership
University Read Models & Projections Module owns all projection responsibility standards.

### Required Projection Responsibilities
- Consume Domain Events
- Update Read Models
- Maintain denormalized data
- Support query optimization

### Projection Restrictions
- MUST NOT execute business rules
- MUST NOT modify aggregates
- MUST NOT publish commands
- MUST NOT access presentation components

### Projection Responsibility Requirements
- All projection responsibilities MUST be documented
- All projection responsibilities MUST be enforced
- All projection responsibilities MUST be validated
- All projection responsibilities MUST be auditable
- All projection responsibility history MUST be maintained

---

## 8. Governance Standards

### Permanent Ownership
University Read Models & Projections Module owns all governance standards.

### Required Governance Rules
- Every Read Model MUST serve one query purpose
- Every Read Model MUST remain read-only
- Every Read Model MUST be rebuildable
- Every Read Model MUST avoid business logic
- Every Read Model MUST use ubiquitous language
- Every Projection MUST be deterministic
- Every Projection MUST be idempotent
- Every Projection MUST consume immutable Domain Events
- Every Projection MUST preserve event ordering where required

### Governance Requirements
- All governance rules MUST be documented
- All governance rules MUST be enforced
- All governance rules MUST be validated
- All governance rules MUST be auditable
- All governance rule history MUST be maintained

---

## 9. Dependency Rules Standards

### Permanent Ownership
University Read Models & Projections Module owns all dependency rules standards.

### Required Dependency Rules
- Domain Event MAY trigger Projection
- Projection MAY update Read Model
- Query Handler MAY consume Read Model
- Projection MUST NOT depend on University Aggregate
- Read Model MUST NOT depend on Write Repository
- Projection MUST NOT depend on Business Rule Implementation
- The read-side remains independent of the write-side

### Allowed Dependencies
```
Domain Event
    |
    v
Projection
    |
    v
Read Model

Query Handler
    |
    v
Read Model
```

### Prohibited Dependencies
```
Projection
    |
    v
University Aggregate

Read Model
    |
    v
Write Repository

Projection
    |
    v
Business Rule Implementation
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
University Read Models & Projections Module owns all implementation rules standards.

### Required Implementation Rules
1. Read Models are query-optimized
2. Read Models are read-only
3. Projections consume Domain Events
4. Projections are deterministic
5. Projections are idempotent
6. Business rules remain in the domain layer
7. Eventual consistency is accepted
8. Read Models are rebuildable
9. CQRS read/write separation is preserved
10. Domain integrity is maintained

### Implementation Requirements
- All implementation rules MUST be documented
- All implementation rules MUST be enforced
- All implementation rules MUST be validated
- All implementation rules MUST be auditable
- All implementation rule history MUST be maintained
