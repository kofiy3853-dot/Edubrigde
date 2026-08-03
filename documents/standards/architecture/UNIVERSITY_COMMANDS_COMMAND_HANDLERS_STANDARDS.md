# University Commands & Command Handlers Standards

LOCKED — Permanent — ADR-163

## Purpose

Establish University command principle standards, command ownership standards, command catalog standards, command handler catalog standards, command execution flow standards, validation flow standards, command responsibility standards, handler responsibility standards, concurrency standards, governance standards, and dependency rule standards for all future University command and handler developments in EduBridge OS.

## University Command Foundation Architecture

```
Command (Immutable Intent)
        |
    Command Handler (Orchestration)
        |
    Application Service (Coordination)
        |
    University Aggregate (Business Rules)
        |
    Repository (Persistence)
        |
    Domain Events (Asynchronous Communication)
```

**Note:** Cross-cutting concerns are shared across all modules via repository-wide services. This module owns ONLY its unique responsibilities.

## Command Principles

### Permanent Ownership
University Commands & Command Handlers Module owns all command principle standards.

### Required Command Principles
- Every Command MUST be intent-based
- Every Command MUST be immutable
- Every Command MUST be independently validatable
- Every Command MUST be technology-independent
- Every Command MUST be business-oriented
- Every Command MUST be versionable

### Principle Requirements
- All principles MUST be documented
- All principles MUST be enforced
- All principles MUST be validated
- All principles MUST be auditable
- All principle history MUST be maintained

---

## 1. Command Ownership Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all command ownership standards.

### Required Command Ownership Rules
- University bounded context owns commands related to university registration
- University bounded context owns commands related to university profile management
- University bounded context owns commands related to campus management
- University bounded context owns commands related to academic program management
- University bounded context owns commands related to admission requirements
- University bounded context owns commands related to tuition management
- University bounded context owns commands related to accreditation updates
- University bounded context owns commands related to intake management
- University bounded context owns commands related to university publication
- University bounded context owns commands related to university archival

### Command Ownership Requirements
- All ownership MUST be documented
- All ownership MUST be enforced
- All ownership MUST be validated
- All ownership MUST be auditable
- All ownership history MUST be maintained

---

## 2. Command Catalog Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all command catalog standards.

### Required Catalog Rules
- All commands MUST be documented
- All commands MUST be versioned
- All command changes MUST be approved
- All command changes MUST be auditable
- All command history MUST be maintained

### Approved Command Categories

#### RegisterUniversityCommand
Purpose: Request registration of a new university.
Contains: Institution Identifier, Official Name, Initial Contact Information, Registration Metadata

#### UpdateUniversityProfileCommand
Purpose: Update institutional profile information.
Contains: UniversityId, Updated Profile Data, Expected Aggregate Version

#### AddCampusCommand
Purpose: Create a new campus.
Contains: UniversityId, Campus Information, Expected Aggregate Version

#### UpdateCampusCommand
Purpose: Modify an existing campus.
Contains: UniversityId, CampusId, Updated Campus Information, Expected Aggregate Version

#### AddAcademicProgramCommand
Purpose: Add a new academic program.
Contains: UniversityId, Program Information, Expected Aggregate Version

#### UpdateAdmissionRequirementsCommand
Purpose: Modify admission requirements.
Contains: UniversityId, ProgramId, Requirement Information, Expected Aggregate Version

#### UpdateTuitionStructureCommand
Purpose: Modify tuition and fee information.
Contains: UniversityId, Tuition Details, Expected Aggregate Version

#### UpdateAccreditationCommand
Purpose: Update accreditation records.
Contains: UniversityId, Accreditation Information, Expected Aggregate Version

#### ScheduleIntakePeriodCommand
Purpose: Create or update an intake period.
Contains: UniversityId, Intake Details, Expected Aggregate Version

#### PublishUniversityCommand
Purpose: Publish a university profile.
Contains: UniversityId, Publication Metadata, Expected Aggregate Version

#### ArchiveUniversityCommand
Purpose: Archive a university.
Contains: UniversityId, Archive Reason, Expected Aggregate Version

### Catalog Requirements
- Each command MUST have clear semantics
- Each command MUST be immutable
- Each command MUST be independently testable
- Each command MUST be documented
- Each command MUST use ubiquitous language

---

## 3. Command Handler Catalog Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all command handler catalog standards.

### Required Handler Catalog Rules
- Each command has exactly one corresponding handler
- A command must never have multiple handlers
- All handlers MUST be documented
- All handlers MUST be versioned
- All handler changes MUST be approved

### Approved Command Handler Categories
- RegisterUniversityCommandHandler
- UpdateUniversityProfileCommandHandler
- AddCampusCommandHandler
- UpdateCampusCommandHandler
- AddAcademicProgramCommandHandler
- UpdateAdmissionRequirementsCommandHandler
- UpdateTuitionStructureCommandHandler
- UpdateAccreditationCommandHandler
- ScheduleIntakePeriodCommandHandler
- PublishUniversityCommandHandler
- ArchiveUniversityCommandHandler

### Handler Catalog Requirements
- Each handler MUST have clear semantics
- Each handler MUST be stateless
- Each handler MUST be independently testable
- Each handler MUST be documented
- Each handler MUST use ubiquitous language

---

## 4. Command Execution Flow Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all command execution flow standards.

### Required Execution Flow Stages
1. Command
2. Structural Validation
3. Command Handler
4. Application Service
5. University Aggregate
6. Repository
7. Domain Events

### Execution Flow Rules
- Each step has a clearly defined responsibility
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

## 5. Validation Flow Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all validation flow standards.

### Required Validation Layers
1. Command structure validation
2. Required field validation
3. Application authorization
4. Aggregate retrieval
5. Domain validation
6. Business policy evaluation
7. Aggregate execution

### Validation Rules
- Business rules remain within the domain layer
- Validation is layered
- All validation flow stages are documented
- All validation flow stages are auditable
- All validation flow history is maintained

### Validation Flow Requirements
- All validation flows MUST be documented
- All validation flows MUST be versioned
- All validation flow changes MUST be approved
- All validation flow changes MUST be auditable
- All validation flow history MUST be maintained

---

## 6. Command Responsibility Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all command responsibility standards.

### Required Command Responsibilities
- Commands MAY contain business identifiers
- Commands MAY contain value data
- Commands MAY contain correlation identifiers
- Commands MAY contain expected aggregate version

### Command Restrictions
- Commands MUST NOT contain business logic
- Commands MUST NOT contain persistence logic
- Commands MUST NOT contain UI state
- Commands MUST NOT contain infrastructure dependencies

### Command Responsibility Requirements
- All command responsibilities MUST be documented
- All command responsibilities MUST be enforced
- All command responsibilities MUST be validated
- All command responsibilities MUST be auditable
- All command responsibility history MUST be maintained

---

## 7. Command Handler Responsibility Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all command handler responsibility standards.

### Required Handler Responsibilities
- Receiving commands
- Coordinating validation
- Loading aggregates
- Invoking Application Services
- Persisting changes
- Coordinating event publication
- Returning execution outcomes

### Handler Restrictions
- Handlers remain orchestration components
- Handlers do not contain business logic
- Handlers do not make authorization decisions
- Handlers do not create domain events directly

### Handler Responsibility Requirements
- All handler responsibilities MUST be documented
- All handler responsibilities MUST be enforced
- All handler responsibilities MUST be validated
- All handler responsibilities MUST be auditable
- All handler responsibility history MUST be maintained

---

## 8. Concurrency Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all concurrency standards.

### Required Concurrency Rules
- Every state-changing command SHOULD include ExpectedAggregateVersion
- Handlers verify optimistic concurrency before persisting updates
- Version conflicts prevent inconsistent modifications
- Concurrency failures are reported to the Application Layer
- Concurrency is documented

### Concurrency Requirements
- All concurrency rules MUST be documented
- All concurrency rules MUST be enforced
- All concurrency rules MUST be validated
- All concurrency rules MUST be auditable
- All concurrency rule history MUST be maintained

---

## 9. Governance Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all governance standards.

### Required Governance Rules
- Every Command MUST represent one business intent
- Every Command MUST be immutable
- Every Command MUST use ubiquitous language
- Every Command MUST be independently testable
- Every Command MUST avoid business logic
- Every Command Handler MUST handle one command
- Every Command Handler MUST be stateless
- Every Command Handler MUST preserve transaction boundaries
- Every Command Handler MUST avoid business rule implementation

### Governance Requirements
- All governance rules MUST be documented
- All governance rules MUST be enforced
- All governance rules MUST be validated
- All governance rules MUST be auditable
- All governance rule history MUST be maintained

---

## 10. Dependency Rules Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all dependency rules standards.

### Required Dependency Rules
- Command MAY depend on Command Handler
- Command Handler MAY depend on Application Service
- Application Service MAY depend on University Aggregate
- Command MUST NOT depend on Database
- Command Handler MUST NOT depend on Presentation Layer
- Command Handler MUST NOT depend on Infrastructure-Specific Business Logic
- Command processing remains independent of transport and persistence technologies

### Allowed Dependencies
```
Command
    |
    v
Command Handler
    |
    v
Application Service
    |
    v
University Aggregate
```

### Prohibited Dependencies
```
Command
    |
    v
Database

Command Handler
    |
    v
Presentation Layer

Command Handler
    |
    v
Infrastructure-Specific Business Logic
```

### Dependency Requirements
- All dependencies MUST be documented
- All dependencies MUST be versioned
- All dependency changes MUST be approved
- All dependency changes MUST be auditable
- All dependency history MUST be maintained

---

## 11. Implementation Rules Standards

### Permanent Ownership
University Commands & Command Handlers Module owns all implementation rules standards.

### Required Implementation Rules
1. Commands represent business intent only
2. Commands are immutable
3. One command maps to one handler
4. Command Handlers are stateless
5. Business rules remain in the domain
6. Validation is layered
7. Optimistic concurrency is enforced
8. Commands are technology-independent
9. Domain events originate from aggregates
10. Domain integrity is preserved

### Implementation Requirements
- All implementation rules MUST be documented
- All implementation rules MUST be enforced
- All implementation rules MUST be validated
- All implementation rules MUST be auditable
- All implementation rule history MUST be maintained
