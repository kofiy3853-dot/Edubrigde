# Kafka Standards

## Status

LOCKED - Sprint 0C, Step 17

## Principle

Event-driven internal communication. API-driven external communication. Kafka is transport only.

## Topic Naming Standards

```
{domain}.{action}.{state}

identity.user.created
identity.password.reset
identity.session.started
academic.course.enrolled
academic.course.completed
career.job.posted
career.application.submitted
financial.payment.completed
financial.budget.created
institution.application.submitted
institution.application.approved
communication.notification.sent
analytics.event.tracked
ai.recommendation.generated
```

Rules:
- Lowercase only
- Dots as separators
- Domain first, then action, then state
- No underscores or hyphens

## Event Standards

Every event includes:
- `eventId` (UUID)
- `eventType` (string)
- `version` (string)
- `timestamp` (Instant)
- `source` (string)
- `metadata` (Map)

## Producer Standards

Every producer must support:
- Validation
- Retries (3 attempts)
- Observability (logging)
- Error handling
- Tracing (correlation IDs)
- Versioning

Workflow:
```
Service -> Validation -> Event Creation -> Producer -> Kafka -> Monitoring
```

## Consumer Standards

Every consumer must support:
- Validation
- Retries (3 attempts)
- Dead Letter Queue
- Monitoring
- Tracing
- Recovery procedures

Workflow:
```
Kafka -> Consumer -> Validation -> Processing -> Monitoring -> Completion -> Audit
```

## Dead Letter Queue Standards

Permanent support:
- Invalid events
- Expired events
- Failed events
- Retried events
- Recovery events

Topic pattern: `{topic}.dlq`

Rules:
- Max 3 retries before DLQ
- DLQ events are logged
- DLQ events are monitored
- Manual recovery required

## Event Versioning Standards

```
Version 1: StudentRegisteredEventV1
Version 2: StudentRegisteredEventV2
Version 3: future support
```

Requirements:
- Backward compatibility
- Event documentation
- Migration strategies
- Version validation

## Ordering Standards

Required ordered processing:
- Payments
- Applications
- Analytics events

Eventual consistency acceptable:
- Notifications
- Recommendations
- AI events

## Consumer Group Standards

```
edubridge-identity     -> Identity events
edubridge-academic     -> Academic events
edubridge-financial    -> Financial events
edubridge-analytics    -> Analytics events
edubridge-notification -> Notification events
edubridge-ai           -> AI events
```

## Monitoring Standards

```
Consumer Lag        -> Alert: >1000
Topic Statistics    -> Messages in/out
Retry Statistics    -> Retry counts
DLQ Statistics      -> DLQ counts
Throughput Metrics  -> Messages/second
Performance Metrics -> Latency
Health Checks       -> Every 30 seconds
Tracing             -> Correlation IDs
```

## Testing Standards

```
Producer Tests      -> Publish, validate, retry
Consumer Tests      -> Consume, process, acknowledge
DLQ Tests           -> Dead letter handling
Integration Tests   -> Full workflow
Performance Tests   -> Throughput, latency
Recovery Tests      -> Failure scenarios
Version Tests       -> Event versioning
```

## Never

```
- Direct domain coupling
- Shared business logic
- Unversioned events
- Silent consumer failures
- Unlimited retries
- Undocumented topics
- Public event exposure
- Kafka as primary database
- Business logic in consumers
- Synchronous event processing
```
