# University Management Standards

## Architecture

```
Global Institution Management Architecture
University Module is single source of truth for all institutional information
No cross-domain duplication
All future domains consume University Module
```

## Entity Structure

```
University Profile (root)
    │
    ├── Accreditation Records
    │
    ├── Institution Classifications
    │
    ├── International Partnerships
    │
    └── Institutional Policies
```

## University Profile Standards

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

## Accreditation Record Standards

```
Accreditation Record Fields:
├── universityId (required, FK to University)
├── accreditationType (INSTITUTIONAL, ACADEMIC, COUNTRY, PROGRAM, PARTNERSHIP)
├── status (ACCREDITED, PENDING, EXPIRED, REVOKED, SUSPENDED)
├── accreditationBody (required)
├── accreditationReference
├── accreditationLevel (NATIONAL, REGIONAL, INTERNATIONAL)
├── grantedDate
├── expirationDate
├── renewalDate
├── verificationUrl
├── documentationUrl
├── notes
├── isActive
└── requiresRenewal
```

## Institution Classification Standards

```
Institution Classification Fields:
├── universityId (required, FK to University)
├── classificationType (SIZE, RESEARCH, FOCUS, REGION, NETWORK, RANKING)
├── classificationValue (required)
├── classificationCode (required)
├── classificationBody
├── effectiveDate
├── expirationDate
├── notes
├── isPrimary
└── isActive
```

## International Partnership Standards

```
International Partnership Fields:
├── partnerUniversityId (required, FK to University)
├── partnerInstitutionName (required)
├── partnerInstitutionCountry (required)
├── partnerInstitutionCode
├── partnershipType (EXCHANGE, STRATEGIC, ACADEMIC, RESEARCH, DUAL_DEGREE)
├── status (ACTIVE, PENDING, SUSPENDED, TERMINATED, EXPIRED)
├── agreementReference
├── startDate
├── endDate
├── renewalDate
├── contactPerson
├── contactEmail
├── contactPhone
├── notes
├── isActive
└── requiresApproval
```

## University Policy Standards

```
University Policy Fields:
├── universityId (required, FK to University)
├── policyType (ADMISSION, ACADEMIC, INSTITUTION, VALIDATION, INTEGRATION, FINANCIAL)
├── name (required)
├── code (required)
├── description
├── policyRule (required, JSON)
├── effect (ALLOW, DENY)
├── priority
├── isMandatory
├── effectiveDate
├── expirationDate
├── isActive
├── requiresReview
└── lastReviewedDate
```

## API Standards

```
/api/v1/academic/universities/
├── {universityId}/accreditation/
│   ├── POST /accreditation
│   ├── GET /accreditation
│   ├── GET /accreditation/type/{type}
│   ├── GET /accreditation/status/{status}
│   ├── PUT /accreditation/{id}
│   ├── POST /accreditation/{id}/deactivate
│   └── POST /accreditation/{id}/activate
│
├── {universityId}/classifications/
│   ├── POST /classifications
│   ├── GET /classifications
│   ├── GET /classifications/type/{type}
│   ├── PUT /classifications/{id}
│   ├── POST /classifications/{id}/deactivate
│   └── POST /classifications/{id}/activate
│
├── {universityId}/partnerships/
│   ├── POST /partnerships
│   ├── GET /partnerships
│   ├── GET /partnerships/type/{type}
│   ├── GET /partnerships/status/{status}
│   ├── GET /partnerships/country/{country}
│   ├── PUT /partnerships/{id}
│   ├── POST /partnerships/{id}/deactivate
│   └── POST /partnerships/{id}/activate
│
└── {universityId}/policies/
    ├── POST /policies
    ├── GET /policies
    ├── GET /policies/type/{type}
    ├── GET /policies/effect/{effect}
    ├── PUT /policies/{id}
    ├── POST /policies/{id}/deactivate
    └── POST /policies/{id}/activate
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Duplicate university records
✗ Cross-domain institution ownership
✗ Accreditation validation bypass
✗ Hardcoded institutional policies
✗ Unauthorized institution modifications
✗ Future domains managing university concerns
✗ Institution information without validation
```

## Required Patterns

```
REQUIRED:
✓ Universities as globally managed entities
✓ Accreditation records are independently verifiable
✓ Classifications are assigned by authorized bodies
✓ Partnerships have lifecycle management
✓ Policies are enforced at system level
✓ Validation before persistence
✓ Audit logging for all operations
✓ API-based consumption by future domains
```

## Validation Rules

```
University Validation:
├── Name must be unique
├── Code must be unique
├── Country code must be valid ISO 3166-1 alpha-3
├── Institution type must be valid
└── Accreditation status must be valid

Accreditation Validation:
├── University must exist
├── Accreditation type must be valid
├── Status must be valid
├── Accreditation body must be provided
└── Expiration date must be in future (if provided)

Classification Validation:
├── University must exist
├── Classification type must be valid
├── Classification value must be provided
├── Classification code must be unique
└── Effective date must be provided

Partnership Validation:
├── University must exist
├── Partnership type must be valid
├── Status must be valid
├── Partner institution name must be provided
└── Partner country must be valid

Policy Validation:
├── University must exist
├── Policy type must be valid
├── Effect must be valid (ALLOW or DENY)
├── Policy rule must be valid JSON
└── Priority must be non-negative
```

## Cross-Domain Integration

```
Future Domains:
├── Applications → uses University Module
├── Scholarships → uses University Module
├── AI Services → uses University Module
├── Learning → uses University Module
└── Career → uses University Module

Integration Pattern:
├── APIs for synchronous access
├── Events for asynchronous updates
├── Caching for performance
└── Audit for compliance
```
