# ADR-008: Kafka Architecture

## Date

2026-01-01

## Status

Accepted

## Context

EduBridge OS needs asynchronous communication between domains without creating tight coupling. Direct service-to-service calls create brittle dependencies and prevent independent scaling.

## Decision

Adopt Event-Driven Architecture (EDA) internally using Apache Kafka. External communication remains API-driven.

### Architecture

```
External Clients
    |
    v
APIs (REST)
    |
    v
Spring Services
    |
    v
Domain Events
    |
    v
Kafka Topics
    |
    v
Event Consumers
    |
    v
Other Domains & Services
```

### Topic Naming

```
{domain}.{action}.{state}

identity.user.created
identity.password.reset
financial.payment.completed
institution.application.submitted
academic.course.completed
```

## Rules

```
1. External communication via APIs
2. Internal communication via events
3. Kafka is transport only, not business logic
4. All events are versioned
5. All consumers handle DLQ
6. All producers validate events
7. No direct domain coupling
8. No permanent storage in Kafka
```

## Alternatives Considered

### Option 1: Direct Service Calls

Benefits:
- Simple implementation
- Synchronous responses

Trade-offs:
- Tight coupling
- Single point of failure
- Hard to scale
- Cascade failures

### Option 2: Message Queue (RabbitMQ)

Benefits:
- Reliable delivery
- Routing flexibility

Trade-offs:
- Less scalable
- No event replay
- No event streaming
- Limited retention

### Option 3: HTTP Events (Webhooks)

Benefits:
- Simple implementation
- No infrastructure

Trade-offs:
- No guaranteed delivery
- No event ordering
- No event replay
- Tight coupling

## Rationale

Kafka provides:
- High throughput
- Event replay
- Event streaming
- Scalability
- Durability
- Loose coupling

## Consequences

### Positive

- Loose coupling
- Independent scaling
- Event replay
- Fault tolerance
- Async processing

### Negative

- Eventual consistency
- Complexity
- Debugging difficulty

### Neutral

- Requires monitoring
- Requires DLQ handling
- Requires event versioning

## Approval

| Role | Name | Date |
|------|------|------|
| Tech Lead | | 2026-01-01 |
| Backend Lead | | 2026-01-01 |
| Project Owner | | 2026-01-01 |

## References

- documents/standards/architecture/TECHNOLOGY_STANDARDS.md
- documents/architecture/KAFKA_ARCHITECTURE.md
