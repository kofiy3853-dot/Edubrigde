# Consultation Management Standards

## Architecture

```
Consultation Management Module
├── Consultation Profile (Consultation Records)
├── Session Record (Session Records)
├── Advisor Profile (Advisor Records)
├── Assessment Record (Assessment Records)
├── Policy Record (Policy Records)
└── Lifecycle Record (Status Tracking)
```

## Entity Standards

### Consultation Profile

```
Consultation Profile
├── ID (UUID)
├── Student ID (required)
├── Advisor ID (required)
├── Consultation Type (50 chars, required)
├── Consultation Status (50 chars, required)
├── Scheduled Date (timestamp, required)
├── Duration (integer, required)
├── Topic (255 chars, required)
├── Description (2000 chars, optional)
├── Priority (20 chars, required)
├── Is Virtual (boolean, required)
├── Meeting Link (500 chars, optional)
├── Notes (2000 chars, optional)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Session Record

```
Session Record
├── ID (UUID)
├── Consultation ID (required)
├── Student ID (required)
├── Advisor ID (required)
├── Session Type (50 chars, required)
├── Session Status (50 chars, required)
├── Scheduled Start (timestamp, required)
├── Scheduled End (timestamp, required)
├── Actual Start (timestamp, optional)
├── Actual End (timestamp, optional)
├── Session Notes (2000 chars, optional)
├── Action Items (2000 chars, optional)
├── Follow Up Required (boolean, required)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Advisor Profile

```
Advisor Profile
├── ID (UUID)
├── User ID (required)
├── Advisor Type (50 chars, required)
├── Specialization (255 chars, optional)
├── Qualifications (2000 chars, optional)
├── Experience Years (integer, optional)
├── Languages (500 chars, optional)
├── Availability Status (50 chars, required)
├── Max Consultations Per Day (integer, required)
├── Current Consultations (integer, required)
├── Rating (5,2 precision, optional)
├── Total Reviews (integer, required)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Assessment Record

```
Assessment Record
├── ID (UUID)
├── Consultation ID (required)
├── Student ID (required)
├── Advisor ID (required)
├── Assessment Type (50 chars, required)
├── Assessment Status (50 chars, required)
├── Assessment Score (5,2 precision, optional)
├── Satisfaction Score (5,2 precision, optional)
├── Effectiveness Score (5,2 precision, optional)
├── Assessment Notes (2000 chars, optional)
├── Recommendations (2000 chars, optional)
├── Assessed At (timestamp, optional)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Policy Record

```
Policy Record
├── ID (UUID)
├── Name (255 chars, required)
├── Description (1000 chars, optional)
├── Policy Type (50 chars, required)
├── Policy Rule (2000 chars, required)
├── Effect (20 chars, required)
├── Priority (integer, required)
├── Conditions (2000 chars, optional)
├── Effective From (date, optional)
├── Effective To (date, optional)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Lifecycle Record

```
Lifecycle Record
├── ID (UUID)
├── Consultation ID (required)
├── Student ID (optional)
├── Previous Status (50 chars, optional)
├── New Status (50 chars, required)
├── Reason (2000 chars, optional)
├── Changed By (255 chars, required)
├── Changed At (timestamp, required)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

## API Standards

### Endpoints (45 endpoints)

```
/api/v1/student-services/consultations/
├── management/ (9 endpoints)
│   ├── POST /management
│   ├── GET /management/{id}
│   ├── GET /management/student/{studentId}
│   ├── GET /management/advisor/{advisorId}
│   ├── GET /management/type/{type}
│   ├── GET /management/status/{status}
│   ├── PUT /management/{id}
│   ├── POST /management/{id}/cancel
│   └── DELETE /management/{id}
├── sessions/ (8 endpoints)
│   ├── POST /sessions
│   ├── GET /sessions/{id}
│   ├── GET /sessions/consultation/{consultationId}
│   ├── GET /sessions/student/{studentId}
│   ├── GET /sessions/status/{status}
│   ├── PUT /sessions/{id}
│   ├── POST /sessions/{id}/complete
│   └── DELETE /sessions/{id}
├── advisors/ (8 endpoints)
│   ├── POST /advisors
│   ├── GET /advisors/{id}
│   ├── GET /advisors/type/{type}
│   ├── GET /advisors/specialization/{specialization}
│   ├── GET /advisors/available
│   ├── PUT /advisors/{id}
│   ├── GET /advisors/{id}/availability
│   └── DELETE /advisors/{id}
├── assessments/ (8 endpoints)
│   ├── POST /assessments
│   ├── GET /assessments/{id}
│   ├── GET /assessments/consultation/{consultationId}
│   ├── GET /assessments/student/{studentId}
│   ├── GET /assessments/type/{type}
│   ├── PUT /assessments/{id}
│   ├── POST /assessments/{id}/complete
│   └── DELETE /assessments/{id}
├── policies/ (8 endpoints)
│   ├── POST /policies
│   ├── GET /policies/{id}
│   ├── GET /policies/type/{type}
│   ├── GET /policies/effect/{effect}
│   ├── GET /policies/active
│   ├── PUT /policies/{id}
│   └── DELETE /policies/{id}
├── lifecycle/ (6 endpoints)
│   ├── POST /lifecycle
│   ├── GET /lifecycle/{id}
│   ├── GET /lifecycle/consultation/{consultationId}
│   ├── GET /lifecycle/student/{studentId}
│   ├── PUT /lifecycle/{id}
│   └── DELETE /lifecycle/{id}
└── management/ (0 endpoints - reserved for future)
```

## Validation Standards

### Consultation Profile Validation

```
REQUIRED:
✓ Student ID is not empty
✓ Advisor ID is not empty
✓ Consultation type is valid
✓ Consultation status is valid
✓ Scheduled date is not empty
✓ Duration is positive
✓ Topic is not empty
✓ Priority is valid

NOT ALLOWED:
✗ Invalid consultation types
✗ Invalid consultation statuses
✗ Past scheduled dates
✗ Duration <= 0
✗ Invalid priorities
```

### Session Record Validation

```
REQUIRED:
✓ Consultation ID is not empty
✓ Student ID is not empty
✓ Advisor ID is not empty
✓ Session type is valid
✓ Session status is valid
✓ Scheduled start is not empty
✓ Scheduled end is not empty
✓ Scheduled start < scheduled end

NOT ALLOWED:
✗ Invalid session types
✗ Invalid session statuses
✗ Scheduled start >= scheduled end
✗ Completed session modifications
```

### Advisor Profile Validation

```
REQUIRED:
✓ User ID is not empty
✓ Advisor type is valid
✓ Availability status is valid
✓ Max consultations per day is positive
✓ Current consultations is non-negative

NOT ALLOWED:
✗ Invalid advisor types
✗ Invalid availability statuses
✗ Max consultations <= 0
✗ Negative current consultations
```

### Assessment Record Validation

```
REQUIRED:
✓ Consultation ID is not empty
✓ Student ID is not empty
✓ Advisor ID is not empty
✓ Assessment type is valid
✓ Assessment status is valid
✓ Assessment score is between 0 and 100 (if provided)
✓ Satisfaction score is between 0 and 100 (if provided)

NOT ALLOWED:
✗ Invalid assessment types
✗ Invalid assessment statuses
✗ Scores outside valid range
✗ Completed assessment modifications
```

### Policy Record Validation

```
REQUIRED:
✓ Name is not empty
✓ Policy type is not empty
✓ Policy rule is not empty
✓ Effect is valid (ALLOW or DENY)
✓ Priority is non-negative
✓ Effective from is not empty
✓ Effective from is before effective to

NOT ALLOWED:
✗ Invalid effects
✗ Negative priorities
✗ Effective from after effective to
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Bypassing consultation validation
✗ Hardcoded consultation policies
✗ Consultation modifications without audit
✗ Cross-domain consultation ownership
✗ Consultation assessments without validation
✗ Future domains managing consultation concerns
✗ Consultation operations without monitoring
```

## Required Patterns

```
REQUIRED:
✓ Human-centered advisory architecture
✓ Repository-wide consultation standards
✓ Security-first consultation validation
✓ Monitoring-first consultation operations
✓ Policy-aware consultation management
✓ Extensible international educational advisory support
✓ Audit logging for all operations
✓ Error handling and retry logic
✓ Idempotent operations
```

## Cross-Domain Integration

```
Future Domains → Consultation Management Module
├── Financial → use Consultation Standards
├── Learning → use Session Management
├── AI Services → use Advisory Management
├── Student Services → use Consultant Standards
└── International Education → use Consultation Profiles

Integration Pattern:
├── APIs for synchronous access
├── Events for asynchronous updates
├── Caching for performance
└── Audit for compliance
```
