# Admissions Management Standards

## Architecture

```
Policy-Driven Admissions Architecture
Admissions Module is single source of truth for all admission decisions
No cross-domain duplication
All future domains consume Admissions Module
```

## Entity Flow

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

## Admission Requirement Standards

```
Admission Requirement Fields:
├── universityId (required, FK to University)
├── programId (optional, FK to Program)
├── requirementType (ACADEMIC, LANGUAGE, AGE, COUNTRY, DOCUMENT, EXPERIENCE, PROGRAM)
├── name (required)
├── description
├── isMandatory
├── validationRule (JSON)
├── minValue
├── maxValue
├── allowedValues (JSON array)
├── weight
├── priority
└── isActive
```

## Eligibility Rule Standards

```
Eligibility Rule Fields:
├── universityId (required, FK to University)
├── programId (optional, FK to Program)
├── ruleType (ACADEMIC, LANGUAGE, AGE, COUNTRY, PROGRAM, COMBINED)
├── name (required)
├── description
├── ruleExpression (required, JSON)
├── effect (ALLOW, DENY, RESTRICT)
├── conditions (required, JSON)
├── priority
├── isMandatory
└── isActive
```

## Academic Validation Standards

```
Academic Validation Fields:
├── universityId (required, FK to University)
├── programId (optional, FK to Program)
├── validationType (GRADE, TRANSCRIPT, QUALIFICATION, CREDENTIAL, EXPERIENCE)
├── name (required)
├── description
├── validationRule (required, JSON)
├── minValue
├── maxValue
├── allowedValues (JSON array)
├── isMandatory
├── weight
└── isActive
```

## Admission Assessment Standards

```
Admission Assessment Fields:
├── universityId (required, FK to University)
├── programId (required, FK to Program)
├── studentId (reference to Identity Domain)
├── status (PENDING, VALIDATING, ELIGIBLE, CONDITIONALLY_ELIGIBLE, RESTRICTED, INELIGIBLE)
├── assessmentType (STANDARD, EXPEDITED, CONDITIONAL, FULL)
├── academicScore
├── languageScore
├── overallScore
├── eligibilityResult (JSON)
├── validationResults (JSON)
├── policyResults (JSON)
├── notes
├── assessedBy
├── assessedAt
├── expirationDate
└── isActive
```

## Admission Policy Standards

```
Admission Policy Fields:
├── universityId (required, FK to University)
├── programId (optional, FK to Program)
├── policyType (ADMISSION, ELIGIBILITY, VALIDATION, ASSESSMENT, COUNTRY, PROGRAM)
├── name (required)
├── code (required)
├── description
├── policyRule (required, JSON)
├── effect (ALLOW, DENY, RESTRICT)
├── priority
├── isMandatory
├── effectiveDate
├── expirationDate
├── isActive
├── requiresReview
└── lastReviewedDate
```

## Admission Lifecycle Standards

```
Admission Lifecycle Fields:
├── assessmentId (required, FK to AdmissionAssessment)
├── status (PENDING, VALIDATING, ELIGIBLE, CONDITIONALLY_ELIGIBLE, RESTRICTED, INELIGIBLE, ARCHIVED)
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
/api/v1/academic/admissions/
├── {universityId}/eligibility/
│   ├── POST /eligibility
│   ├── GET /eligibility
│   ├── GET /eligibility/type/{type}
│   ├── GET /eligibility/effect/{effect}
│   ├── PUT /eligibility/{id}
│   ├── POST /eligibility/{id}/deactivate
│   └── POST /eligibility/{id}/activate
│
├── {universityId}/validation/
│   ├── POST /validation
│   ├── GET /validation
│   ├── GET /validation/type/{type}
│   ├── PUT /validation/{id}
│   ├── POST /validation/{id}/deactivate
│   └── POST /validation/{id}/activate
│
├── {universityId}/assessments/
│   ├── POST /assessments
│   ├── GET /assessments
│   ├── GET /assessments/program/{programId}
│   ├── GET /assessments/student/{studentId}
│   ├── GET /assessments/status/{status}
│   ├── PUT /assessments/{id}
│   ├── POST /assessments/{id}/deactivate
│   └── POST /assessments/{id}/activate
│
├── {universityId}/policies/
│   ├── POST /policies
│   ├── GET /policies
│   ├── GET /policies/type/{type}
│   ├── GET /policies/effect/{effect}
│   ├── PUT /policies/{id}
│   ├── POST /policies/{id}/deactivate
│   └── POST /policies/{id}/activate
│
└── {universityId}/lifecycle/
    ├── POST /lifecycle
    ├── GET /lifecycle/assessment/{assessmentId}
    ├── GET /lifecycle/assessment/{assessmentId}/current
    ├── GET /lifecycle/status/{status}
    ├── POST /lifecycle/{id}/status
    ├── POST /lifecycle/{id}/deactivate
    └── POST /lifecycle/{id}/activate
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Duplicate admission records
✗ Cross-domain admission ownership
✗ Eligibility validation bypass
✗ Hardcoded admission policies
✗ Unauthorized admission modifications
✗ Future domains managing admission concerns
✗ Admission decisions without validation
```

## Required Patterns

```
REQUIRED:
✓ Admission decisions are policy-driven
✓ Eligibility is validated for every admission
✓ Academic validation is required
✓ Assessments are tracked
✓ Lifecycle is managed
✓ Validation before persistence
✓ Audit logging for all operations
✓ API-based consumption by future domains
```

## Validation Rules

```
Admission Requirement Validation:
├── University must exist
├── Requirement type must be valid
├── Name must be provided
├── Validation rule must be valid JSON
└── Weight must be non-negative

Eligibility Rule Validation:
├── University must exist
├── Rule type must be valid
├── Effect must be valid (ALLOW, DENY, RESTRICT)
├── Rule expression must be valid JSON
└── Conditions must be valid JSON

Academic Validation Validation:
├── University must exist
├── Validation type must be valid
├── Name must be provided
├── Validation rule must be valid JSON
└── Weight must be non-negative

Admission Assessment Validation:
├── University must exist
├── Program must exist
├── Status must be valid
├── Assessment type must be valid
└── Scores must be non-negative

Admission Policy Validation:
├── University must exist
├── Policy type must be valid
├── Effect must be valid (ALLOW, DENY, RESTRICT)
├── Policy rule must be valid JSON
└── Priority must be non-negative

Admission Lifecycle Validation:
├── Assessment must exist
├── Status must be valid
├── Effective date must be provided
├── Changed by must be provided
└── Reason should be provided
```

## Cross-Domain Integration

```
Future Domains:
├── Applications → uses Admissions Module
├── Scholarships → uses Admissions Module
├── AI Services → uses Admissions Module
├── Financial → uses Admissions Module
└── Learning → uses Admissions Module

Integration Pattern:
├── APIs for synchronous access
├── Events for asynchronous updates
├── Caching for performance
└── Audit for compliance
```
