# ADR-024: Cost Management Architecture

## Status

**LOCKED** - This decision is permanent and cannot be changed.

## Context

EduBridge OS requires comprehensive cost management capabilities for educational costs. The Cost Management Module is the permanent owner of all cost-related operations including:

- Cost categories and classification
- Individual cost items and their management
- Cost breakdowns for programs
- Cost comparisons between options
- Cost estimates for students
- Cost policies and rules

## Decision

**Cost Management is the permanent owner of educational cost intelligence.**

### Architecture

```
Cost Management Module
    │
    ├── Cost Categories
    │   ├── Hierarchical Classification
    │   ├── Category Codes (TUITION, ACCOMMODATION, etc.)
    │   └── Parent-Child Relationships
    │
    ├── Cost Items
    │   ├── Individual Cost Records
    │   ├── University/Program Association
    │   ├── Mandatory vs Optional
    │   └── Refund Policies
    │
    ├── Cost Breakdowns
    │   ├── Program-Level Cost Summaries
    │   ├── Category-Wise Totals
    │   ├── Grand Total Calculations
    │   └── Validity Periods
    │
    ├── Cost Comparisons
    │   ├── Multi-University Comparison
    │   ├── Side-by-Side Analysis
    │   ├── Recommendation Engine
    │   └── Student-Driven Selection
    │
    ├── Cost Estimates
    │   ├── Student-Specific Estimates
    │   ├── Affordability Scoring
    │   ├── Risk Level Assessment
    │   └── Financial Gap Analysis
    │
    └── Cost Policies
        ├── Discount Rules
        ├── Scholarship Rules
        ├── Refund Rules
        └── Payment Plan Rules
```

### Cost Categories

| Code | Description |
|------|-------------|
| TUITION | Tuition fees |
| ACCOMMODATION | Housing and accommodation |
| TRANSPORTATION | Travel and transportation |
| FOOD | Meal plans and food costs |
| HEALTHCARE | Health insurance and medical |
| ACADEMIC | Books, supplies, equipment |
| VISA | Visa and immigration costs |
| OTHER | Miscellaneous costs |

### Cost Item Types

| Type | Description |
|------|-------------|
| MANDATORY | Required costs for enrollment |
| OPTIONAL | Optional costs students can choose |
| ESTIMATED | Approximate costs |
| FIXED | Confirmed costs |
| REFUNDABLE | Costs that can be refunded |
| NON_REFUNDABLE | Costs that cannot be refunded |

### Cost Estimate Risk Levels

| Level | Score Range | Description |
|-------|-------------|-------------|
| LOW | 80-100 | High affordability |
| MEDIUM | 50-79 | Moderate affordability |
| HIGH | 20-49 | Low affordability |
| CRITICAL | 0-19 | Very low affordability |

## Consequences

### Positive
- Single source of truth for all cost data
- Consistent cost management across platform
- Student-centric cost intelligence
- Policy-driven cost operations

### Negative
- All cost operations must go through Cost Management
- No bypassing cost validation
- No cross-domain cost ownership

## Integration Points

```
Financial Domain → Cost Management Module
├── Financial Profiles → use Cost Items
├── Cost Estimates → use Cost Breakdowns
├── Cost Comparisons → use Cost Items
└── Cost Policies → apply to Cost Items
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Other domains managing cost data directly
✗ Bypassing cost category classification
✗ Hardcoded cost calculations
✗ Cost modifications without audit
✗ Cross-domain cost duplication
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
```
