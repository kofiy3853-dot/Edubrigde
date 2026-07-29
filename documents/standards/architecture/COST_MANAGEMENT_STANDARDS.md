# Cost Management Standards

## Architecture

```
Cost Management Module
├── Categories (Hierarchical Classification)
├── Items (Individual Cost Records)
├── Breakdowns (Program-Level Summaries)
├── Comparisons (Multi-University Analysis)
├── Estimates (Student-Specific Calculations)
└── Policies (Discount/Refund Rules)
```

## Entity Standards

### Cost Category

```
Cost Category
├── ID (UUID)
├── Name (100 chars, required)
├── Code (50 chars, unique, required)
├── Description (500 chars, optional)
├── Parent Code (50 chars, optional)
├── Sort Order (integer, required)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Cost Item

```
Cost Item
├── ID (UUID)
├── University ID (required)
├── Program ID (optional)
├── Category Code (50 chars, required)
├── Name (255 chars, required)
├── Description (1000 chars, optional)
├── Amount (15,2 precision, required)
├── Currency Code (3 chars, required)
├── Frequency (20 chars, required)
├── Academic Year (integer, required)
├── Is Mandatory (boolean, required)
├── Is Estimated (boolean, required)
├── Is Refundable (boolean, required)
├── Refund Policy (1000 chars, optional)
├── Source (255 chars, optional)
├── Effective From (date, optional)
├── Effective To (date, optional)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Cost Breakdown

```
Cost Breakdown
├── ID (UUID)
├── University ID (required)
├── Program ID (required)
├── Country Code (3 chars, required)
├── Name (255 chars, required)
├── Description (1000 chars, optional)
├── Academic Year (integer, required)
├── Total Tuition (15,2 precision, required)
├── Total Accommodation (15,2 precision, required)
├── Total Living Expenses (15,2 precision, required)
├── Total Other Costs (15,2 precision, required)
├── Grand Total (15,2 precision, required)
├── Currency Code (3 chars, required)
├── Is Estimated (boolean, required)
├── Source (255 chars, optional)
├── Valid From (date, required)
├── Valid To (date, optional)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Cost Comparison

```
Cost Comparison
├── ID (UUID)
├── Student ID (required)
├── Name (255 chars, required)
├── Description (1000 chars, optional)
├── Country Code (3 chars, required)
├── Academic Year (integer, required)
├── Currency Code (3 chars, required)
├── University 1 ID (required)
├── University 1 Program ID (required)
├── University 1 Total Cost (15,2 precision, required)
├── University 2 ID (optional)
├── University 2 Program ID (optional)
├── University 2 Total Cost (15,2 precision, optional)
├── University 3 ID (optional)
├── University 3 Program ID (optional)
├── University 3 Total Cost (15,2 precision, optional)
├── Comparison Result (2000 chars, optional)
├── Recommended University ID (optional)
├── Is Completed (boolean, required)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Cost Estimate

```
Cost Estimate
├── ID (UUID)
├── Student ID (required)
├── University ID (required)
├── Program ID (required)
├── Country Code (3 chars, required)
├── Currency Code (3 chars, required)
├── Academic Year (integer, required)
├── Total Tuition (15,2 precision, required)
├── Total Accommodation (15,2 precision, required)
├── Total Living Expenses (15,2 precision, required)
├── Total Other Costs (15,2 precision, required)
├── Grand Total (15,2 precision, required)
├── Available Funds (15,2 precision, required)
├── Financial Gap (15,2 precision, required)
├── Affordability Score (5,2 precision, optional)
├── Risk Level (20 chars, optional)
├── Is Finalized (boolean, required)
├── Finalized At (date, optional)
├── Is Active (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

### Cost Policy

```
Cost Policy
├── ID (UUID)
├── University ID (optional)
├── Country Code (3 chars, optional)
├── Category Code (50 chars, optional)
├── Name (255 chars, required)
├── Description (1000 chars, optional)
├── Policy Type (50 chars, required)
├── Policy Rule (2000 chars, required)
├── Discount Percentage (5,2 precision, optional)
├── Discount Amount (15,2 precision, optional)
├── Min Amount (15,2 precision, optional)
├── Max Discount (15,2 precision, optional)
├── Effective From (date, optional)
├── Effective To (date, optional)
├── Is Active (boolean, required)
├── Requires Approval (boolean, required)
├── Created At (timestamp)
├── Updated At (timestamp)
└── Version (optimistic locking)
```

## API Standards

### Endpoints (55 endpoints)

```
/api/v1/financial/cost-management/
├── categories/ (7 endpoints)
│   ├── POST /categories
│   ├── GET /categories/{id}
│   ├── GET /categories
│   ├── GET /categories/root
│   ├── GET /categories/parent/{parentCode}
│   ├── PUT /categories/{id}
│   └── DELETE /categories/{id}
├── items/ (11 endpoints)
│   ├── POST /items
│   ├── GET /items/{id}
│   ├── GET /items/university/{universityId}
│   ├── GET /items/program/{programId}
│   ├── GET /items/category/{categoryCode}
│   ├── GET /items/university/{universityId}/category/{categoryCode}
│   ├── GET /items/university/{universityId}/year/{academicYear}
│   ├── GET /items/university/{universityId}/program/{programId}
│   ├── GET /items/university/{universityId}/mandatory
│   ├── GET /items/university/{universityId}/optional
│   ├── PUT /items/{id}
│   └── DELETE /items/{id}
├── breakdowns/ (9 endpoints)
│   ├── POST /breakdowns
│   ├── GET /breakdowns/{id}
│   ├── GET /breakdowns/university/{universityId}
│   ├── GET /breakdowns/program/{programId}
│   ├── GET /breakdowns/country/{countryCode}
│   ├── GET /breakdowns/university/{universityId}/program/{programId}
│   ├── GET /breakdowns/country/{countryCode}/year/{academicYear}
│   ├── PUT /breakdowns/{id}
│   └── DELETE /breakdowns/{id}
├── comparisons/ (8 endpoints)
│   ├── POST /comparisons
│   ├── GET /comparisons/{id}
│   ├── GET /comparisons/student/{studentId}
│   ├── GET /comparisons/student/{studentId}/completed
│   ├── GET /comparisons/student/{studentId}/incomplete
│   ├── PUT /comparisons/{id}
│   ├── POST /comparisons/{id}/complete
│   └── DELETE /comparisons/{id}
├── estimates/ (9 endpoints)
│   ├── POST /estimates
│   ├── GET /estimates/{id}
│   ├── GET /estimates/student/{studentId}
│   ├── GET /estimates/student/{studentId}/draft
│   ├── GET /estimates/student/{studentId}/finalized
│   ├── GET /estimates/risk/{riskLevel}
│   ├── PUT /estimates/{id}
│   ├── POST /estimates/{id}/finalize
│   └── DELETE /estimates/{id}
└── policies/ (11 endpoints)
    ├── POST /policies
    ├── GET /policies/{id}
    ├── GET /policies/university/{universityId}
    ├── GET /policies/country/{countryCode}
    ├── GET /policies/type/{policyType}
    ├── GET /policies/category/{categoryCode}
    ├── GET /policies/university/{universityId}/type/{policyType}
    ├── GET /policies/country/{countryCode}/category/{categoryCode}
    ├── GET /policies/approval-required
    ├── PUT /policies/{id}
    └── DELETE /policies/{id}
```

## Validation Standards

### Cost Category Validation

```
REQUIRED:
✓ Code is unique
✓ Code is not empty
✓ Name is not empty
✓ Sort order is non-negative
✓ Parent code exists (if provided)

NOT ALLOWED:
✗ Duplicate codes
✗ Self-referencing parent
✗ Circular parent relationships
```

### Cost Item Validation

```
REQUIRED:
✓ University ID is not empty
✓ Category code is not empty
✓ Name is not empty
✓ Amount is positive
✓ Currency code is valid
✓ Frequency is valid
✓ Academic year is valid

NOT ALLOWED:
✗ Negative amounts
✗ Invalid currency codes
✗ Invalid frequencies
✗ Past academic years
```

### Cost Breakdown Validation

```
REQUIRED:
✓ University ID is not empty
✓ Program ID is not empty
✓ Country code is not empty
✓ Name is not empty
✓ Academic year is valid
✓ Grand total is positive
✓ Currency code is valid
✓ Valid from is not empty

NOT ALLOWED:
✗ Grand total mismatch with component totals
✗ Invalid country codes
✗ Valid from after valid to
```

### Cost Estimate Validation

```
REQUIRED:
✓ Student ID is not empty
✓ University ID is not empty
✓ Program ID is not empty
✓ Country code is not empty
✓ Currency code is not empty
✓ Academic year is valid
✓ Grand total is positive

NOT ALLOWED:
✗ Negative financial gap
✗ Invalid risk level
✗ Finalized estimate modifications
```

### Cost Policy Validation

```
REQUIRED:
✓ Name is not empty
✓ Policy type is not empty
✓ Policy rule is not empty
✓ Discount percentage is between 0 and 100
✓ Discount amount is non-negative
✓ Max discount is non-negative
✓ Effective from is not empty
✓ Effective from is before effective to

NOT ALLOWED:
✗ Discount percentage > 100
✗ Negative discount amounts
✗ Effective from after effective to
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Bypassing cost category validation
✗ Hardcoded cost calculations
✗ Cost modifications without audit
✗ Cross-domain cost ownership
✗ Cost assessments without validation
✗ Future domains managing cost concerns
✗ Cost operations without monitoring
```

## Required Patterns

```
REQUIRED:
✓ All costs categorized properly
✓ Cost items validated before persistence
✓ Cost breakdowns calculated correctly
✓ Cost comparisons fair and unbiased
✓ Cost estimates student-specific
✓ Cost policies applied consistently
✓ Audit logging for all operations
✓ Monitoring for cost operations
✓ Error handling and retry logic
✓ Idempotent operations
```

## Cross-Domain Integration

```
Financial Domain → Cost Management Module
├── Financial Profiles → use Cost Items
├── Cost Estimates → use Cost Breakdowns
├── Cost Comparisons → use Cost Items
└── Cost Policies → apply to Cost Items

Future Domains → Cost Management Module
├── Scholarships → use Cost Items
├── Budgeting → use Cost Breakdowns
├── AI Services → use Cost Estimates
└── Learning → use Cost Policies
```
