# Admissions Read Models & Projections Standards

LOCKED — Permanent — ADR-182

## Purpose

Establish Admissions read model principle standards, projection principle standards, read model catalog standards, projection catalog standards, event-to-projection mapping standards, governance standards, and dependency rule standards for all future Admissions read model and projection developments in EduBridge OS.

## Read Model Principles

### Required Principles
- Every Read Model MUST be query-optimized
- Every Read Model MUST be read-only
- Every Read Model MUST be derived from Domain Events
- Every Read Model MUST be disposable and rebuildable
- Every Projection MUST be deterministic
- Every Projection MUST be idempotent

## Read Model Catalog

### Approved Read Models
- ApplicationSummaryReadModel
- ApplicationStatusReadModel
- EligibilityResultReadModel
- ReviewReadModel
- DecisionReadModel
- OfferReadModel
- WaitlistReadModel

## Governance Rules
- All read models MUST be documented
- All read models MUST be versioned
- All read model changes MUST be approved
- All read model changes MUST be auditable
- All read model history MUST be maintained
