# Academic Domain Standards

## Architecture

```
Institution-Centric Academic Architecture
Universities own Programs
Programs own Admission Requirements
No cross-domain duplication
All future domains consume Academic Domain
```

## Entity Hierarchy

```
University (root)
    │
    ├── Programs
    │   └── Admission Requirements
    │
    └── University-Wide Requirements
```

## University Standards

```
University Fields:
├── name (required)
├── code (unique, required)
├── countryCode (required)
├── city
├── website
├── institutionType (PUBLIC, PRIVATE, TECHNICAL, INTERNATIONAL, PARTNER)
├── accreditationStatus (ACCREDITED, PENDING, NOT_ACCREDITED)
├── accreditationBody
├── accreditationDate
├── logoUrl
├── description
├── establishedDate
├── phone
├── email
├── address
├── isActive
└── isPartner
```

## Program Standards

```
Program Fields:
├── universityId (required, FK to University)
├── name (required)
├── code (unique, required)
├── level (UNDERGRADUATE, DIPLOMA, CERTIFICATE, FOUNDATION, POSTGRADUATE)
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

## Admission Requirement Standards

```
Admission Requirement Fields:
├── universityId (required, FK to University)
├── programId (optional, FK to Program)
├── requirementType (ACADEMIC, LANGUAGE, AGE, COUNTRY, PROGRAM, DOCUMENT)
├── name (required)
├── description
├── isMandatory
├── validationRule (JSON)
├── minValue
├── maxValue
├── allowedValues (JSON array)
├── isActive
└── priority
```

## API Standards

```
/api/v1/academic/
├── universities/
│   ├── POST /universities
│   ├── GET /universities
│   ├── GET /universities/{id}
│   ├── GET /universities/code/{code}
│   ├── GET /universities/country/{countryCode}
│   ├── GET /universities/type/{institutionType}
│   ├── GET /universities/partners
│   ├── PUT /universities/{id}
│   ├── POST /universities/{id}/deactivate
│   └── POST /universities/{id}/activate
│
├── programs/
│   ├── POST /programs
│   ├── GET /programs
│   ├── GET /programs/{id}
│   ├── GET /programs/code/{code}
│   ├── GET /programs/university/{universityId}
│   ├── GET /programs/level/{level}
│   ├── PUT /programs/{id}
│   ├── POST /programs/{id}/deactivate
│   └── POST /programs/{id}/activate
│
└── admissions/
    ├── POST /admissions
    ├── GET /admissions/university/{universityId}
    ├── GET /admissions/program/{programId}
    ├── GET /admissions/type/{requirementType}
    ├── PUT /admissions/{id}
    └── POST /admissions/{id}/deactivate
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Duplicate university records
✗ Duplicate program ownership
✗ Cross-domain academic ownership
✗ Admission validation bypass
✗ Hardcoded academic policies
✗ Unauthorized academic access
✗ Future domains managing academic concerns
```

## Required Patterns

```
REQUIRED:
✓ Universities as root of hierarchy
✓ Programs belong to universities
✓ Admission requirements belong to universities/programs
✓ Validation before persistence
✓ Audit logging for all operations
✓ API-based consumption by future domains
✓ Event-driven notifications
```

## Validation Rules

```
University Validation:
├── Name must be unique
├── Code must be unique
├── Country code must be valid ISO 3166-1 alpha-3
├── Institution type must be valid
└── Accreditation status must be valid

Program Validation:
├── Name must be unique per university
├── Code must be unique
├── Level must be valid
├── Duration must be positive
├── University must exist
└── Language code must be valid

Admission Requirement Validation:
├── Name must be provided
├── Type must be valid
├── University must exist
├── Program must exist (if specified)
└── Validation rule must be valid JSON
```

## Cross-Domain Integration

```
Future Domains:
├── Applications → uses Academic Domain
├── Scholarships → uses Academic Domain
├── AI Services → uses Academic Domain
├── Learning → uses Academic Domain
└── Career → uses Academic Domain

Integration Pattern:
├── APIs for synchronous access
├── Events for asynchronous updates
├── Caching for performance
└── Audit for compliance
```
