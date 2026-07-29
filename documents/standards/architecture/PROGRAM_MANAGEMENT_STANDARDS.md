# Program Management Standards

## Architecture

```
Global Academic Program Architecture
Program Module is single source of truth for all program information
No cross-domain duplication
All future domains consume Program Module
```

## Entity Structure

```
Program (root)
    │
    ├── Academic Level
    │
    ├── Program Requirements
    │
    ├── Duration Policies
    │
    └── Program Lifecycle
```

## Program Catalog Standards

```
Program Fields:
├── universityId (required, FK to University)
├── name (required)
├── code (unique, required)
├── level (required)
├── fieldOfStudy (required)
├── durationYears (required)
├── durationMonths
├── creditsRequired
├── description
├── requirements (JSON)
├── languageOfInstruction
├── tuitionFeeAmount
├── tuitionFeeCurrency
├── isActive
└── isOnline
```

## Academic Level Standards

```
Academic Level Fields:
├── name (required, unique)
├── code (required, unique)
├── description
├── minDurationYears (required)
├── maxDurationYears (required)
├── minDurationMonths
├── maxDurationMonths
├── typicalDurationYears
├── typicalDurationMonths
├── minCredits
├── maxCredits
├── typicalCredits
├── requiresThesis
├── requiresInternship
├── requiresResearch
├── sortOrder
└── isActive
```

## Program Requirement Standards

```
Program Requirement Fields:
├── programId (required, FK to Program)
├── requirementType (ACADEMIC, LANGUAGE, AGE, COUNTRY, DOCUMENT, EXPERIENCE)
├── name (required)
├── description
├── isMandatory
├── validationRule (JSON)
├── minValue
├── maxValue
├── allowedValues (JSON array)
├── weight
├── isActive
└── sortOrder
```

## Duration Policy Standards

```
Duration Policy Fields:
├── programId (required, FK to Program)
├── durationType (FULL_TIME, PART_TIME, ACCELERATED, FLEXIBLE)
├── name (required)
├── description
├── minDurationYears (required)
├── maxDurationYears (required)
├── minDurationMonths
├── maxDurationMonths
├── defaultDurationYears
├── defaultDurationMonths
├── creditsPerYear
├── maxCreditsPerSemester
├── maxExtensions
├── extensionDurationMonths
├── effectiveDate
├── expirationDate
└── isActive
```

## Program Lifecycle Standards

```
Program Lifecycle Fields:
├── programId (required, FK to Program)
├── status (PENDING, VALIDATING, ACTIVE, RESTRICTED, SUSPENDED, DEPRECATED, ARCHIVED)
├── previousStatus
├── reason
├── changedBy
├── effectiveDate (required)
├── expirationDate
├── reviewDate
├── notes
├── isCurrent
└── isActive
```

## API Standards

```
/api/v1/academic/programs/
├── levels/
│   ├── POST /levels
│   ├── GET /levels
│   ├── GET /levels/active
│   ├── GET /levels/ordered
│   ├── GET /levels/{id}
│   ├── GET /levels/code/{code}
│   ├── PUT /levels/{id}
│   ├── POST /levels/{id}/deactivate
│   └── POST /levels/{id}/activate
│
├── {programId}/requirements/
│   ├── POST /requirements
│   ├── GET /requirements
│   ├── GET /requirements/type/{type}
│   ├── GET /requirements/mandatory
│   ├── PUT /requirements/{id}
│   ├── POST /requirements/{id}/deactivate
│   └── POST /requirements/{id}/activate
│
├── {programId}/durations/
│   ├── POST /durations
│   ├── GET /durations
│   ├── GET /durations/type/{type}
│   ├── PUT /durations/{id}
│   ├── POST /durations/{id}/deactivate
│   └── POST /durations/{id}/activate
│
└── {programId}/lifecycle/
    ├── POST /lifecycle
    ├── GET /lifecycle
    ├── GET /lifecycle/current
    ├── GET /lifecycle/status/{status}
    ├── POST /lifecycle/{id}/status
    ├── POST /lifecycle/{id}/deactivate
    └── POST /lifecycle/{id}/activate
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Duplicate program records
✗ Cross-domain program ownership
✗ Requirement validation bypass
✗ Hardcoded academic policies
✗ Unauthorized program modifications
✗ Future domains managing program concerns
✗ Program information without validation
```

## Required Patterns

```
REQUIRED:
✓ Programs as first-class academic entities
✓ Academic levels are standardized
✓ Requirements are program-specific
✓ Duration policies are configurable
✓ Lifecycle is tracked
✓ Validation before persistence
✓ Audit logging for all operations
✓ API-based consumption by future domains
```

## Validation Rules

```
Program Validation:
├── Name must be unique per university
├── Code must be unique
├── Level must be valid academic level
├── Duration must be positive
├── University must exist
└── Language code must be valid

Academic Level Validation:
├── Name must be unique
├── Code must be unique
├── Duration ranges must be valid
├── Credits must be non-negative
└── Thesis/internship requirements must be valid

Requirement Validation:
├── Program must exist
├── Requirement type must be valid
├── Name must be provided
├── Validation rule must be valid JSON
└── Weight must be non-negative

Duration Policy Validation:
├── Program must exist
├── Duration type must be valid
├── Duration ranges must be valid
├── Extension rules must be valid
└── Effective/expiration dates must be valid

Lifecycle Validation:
├── Program must exist
├── Status must be valid
├── Effective date must be provided
├── Changed by must be provided
└── Reason should be provided
```

## Cross-Domain Integration

```
Future Domains:
├── Applications → uses Program Module
├── Scholarships → uses Program Module
├── AI Services → uses Program Module
├── Financial → uses Program Module
└── Learning → uses Program Module

Integration Pattern:
├── APIs for synchronous access
├── Events for asynchronous updates
├── Caching for performance
└── Audit for compliance
```
