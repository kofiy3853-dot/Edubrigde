# ADR-030: Mentorship Intelligence Architecture

## Status

ACCEPTED (LOCKED)

## Context

EduBridge OS requires a Mentorship Services Domain that serves as the permanent owner of all mentorship intelligence. The Mentorship Services Domain manages mentor-student matching, guidance delivery, growth tracking, and career mentorship across the platform.

## Decision

The Mentorship Services Domain is the permanent owner of all mentorship intelligence in EduBridge OS.

### Core Responsibilities

1. **Mentorship Profile Management**: Student mentorship profiles, interests, and goals
2. **Matching Intelligence**: Mentor-student matching algorithms and compatibility scoring
3. **Mentor Management**: Mentor profiles, expertise areas, and availability
4. **Guidance Delivery**: Structured guidance sessions and learning plans
5. **Growth Tracking**: Student progress, milestones, and achievement records
6. **Lifecycle Management**: Complete mentorship lifecycle from enrollment to completion

### Architecture Principles

1. **Student-Centric**: All mentorship intelligence serves student success
2. **Compatibility-First**: Matching prioritizes student goals and mentor expertise
3. **Progress-Oriented**: Growth tracking drives continuous improvement
4. **Career-Linked**: Mentorship connects directly to career development

### Domain Boundaries

- **Owns**: MentorshipProfile, MatchingRecord, MentorProfile, GuidanceRecord, GrowthRecord, LifecycleRecord
- **Reads**: Student profiles from Identity Domain, academic records from Academic Domain
- **Publishes**: Mentorship events to Kafka
- **Integrates**: Career Domain for career-linked mentorship

## Consequences

- All mentorship intelligence is centralized in the Mentorship Services Domain
- Cross-domain queries use Kafka events for eventual consistency
- The domain provides REST APIs for all mentorship operations
- All mentorship data changes are published as Kafka events
