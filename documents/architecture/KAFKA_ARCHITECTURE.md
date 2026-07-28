# Kafka Responsibilities

## Status

LOCKED - Sprint 0B, Step 08

## Principle

Kafka enables event-driven, decoupled service communication.

## Responsibilities

### 1. Notification Events

```
Events:
  - UserRegistered -> Send welcome email
  - PaymentCompleted -> Send receipt
  - UniversityMatched -> Send notification
  - ApplicationSubmitted -> Send confirmation

Consumers:
  - Communication Service
  - Email Service (future)
  - Push Service (future)
```

### 2. Analytics Events

```
Events:
  - PageView -> Track user behavior
  - SearchPerformed -> Track search patterns
  - UniversityViewed -> Track interest
  - ApplicationStarted -> Track funnel

Consumers:
  - Analytics Service
  - Reporting Service (future)
```

### 3. Audit Events

```
Events:
  - UserLogin -> Log authentication
  - ProfileUpdated -> Log data change
  - PaymentProcessed -> Log financial action
  - RoleChanged -> Log permission change

Consumers:
  - Analytics Service
  - Compliance Service (future)
```

### 4. Application Events

```
Events:
  - CourseEnrolled -> Update academic records
  - GradePosted -> Update transcript
  - ApplicationSubmitted -> Trigger review
  - ApplicationAccepted -> Trigger next steps

Consumers:
  - Academic Service
  - Career Service
  - Financial Service
```

### 5. Payment Events

```
Events:
  - PaymentInitiated -> Start processing
  - PaymentCompleted -> Confirm transaction
  - PaymentFailed -> Handle error
  - RefundIssued -> Process refund

Consumers:
  - Financial Service
  - Analytics Service
  - Communication Service
```

## Event Structure

```json
{
  "eventId": "uuid",
  "eventType": "UserRegistered",
  "aggregateId": "user-uuid",
  "timestamp": "2026-01-01T00:00:00Z",
  "version": 1,
  "source": "identity-service",
  "data": {
    "userId": "uuid",
    "email": "user@example.com",
    "role": "STUDENT"
  },
  "metadata": {
    "traceId": "abc-123",
    "correlationId": "def-456"
  }
}
```

## Topic Naming

```
{domain}.{entity}.{event}

Examples:
  identity.user.registered
  identity.user.updated
  academic.course.enrolled
  financial.payment.completed
  institution.university.matched
```

## Kafka Rules

```
1. Events are immutable
2. Events contain all necessary data
3. Events are idempotent
4. Events include correlation IDs
5. Events are versioned
6. Producers don't know about consumers
7. Consumers handle duplicate delivery
```

## What Kafka Must Never Do

```
NEVER:
  - Store business data permanently
  - Replace database queries
  - Be used for synchronous communication
  - Store sensitive data unencrypted
  - Skip event validation
  - Allow unbounded topics
```

## Scaling

```
Phase 1: Single broker, single partition
Phase 2: Multiple brokers, multiple partitions
Phase 3: Consumer groups for parallel processing
Phase 4: Schema registry for event evolution
```

## Never

```
- Use for synchronous communication
- Store business data permanently
- Skip event validation
- Skip event versioning
- Skip correlation IDs
- Expose Kafka publicly
- Skip monitoring
```
