# ADR-009: MinIO Metadata-First Object Storage Architecture

## Status

**LOCKED** - Permanent architectural decision

## Context

EduBridge OS requires secure, scalable object storage for documents, certificates, learning resources, and media assets. We need to decide how to manage binary files and their associated metadata while maintaining clear separation of concerns.

## Decision

**Metadata-First Object Storage Architecture.** MinIO stores files and binary objects only. PostgreSQL remains responsible for metadata, ownership, permissions, relationships, and audit information.

### Storage Responsibilities

```
MinIO stores:                    PostgreSQL stores:
- Binary files                   - File metadata
- Documents                      - Ownership
- Images                         - Permissions
- Videos (future)                - Versions
- Certificates                   - Audit records
- Learning resources             - Relationships
- Temporary uploads              - Business records
- Media assets                   - Access policies
- Generated documents            - Retention policies
```

### Required Architecture

```
Application Layer
        |
Validation Layer
        |
Storage Services
        |
Security Layer
        |
   +----+----+
   |         |
MinIO   PostgreSQL
   |         |
Binary    Metadata
Files
```

### Bucket Structure

8 isolated buckets with clear responsibilities:

```
edubridge-student-documents      (student uploads)
edubridge-student-certificates   (certificates)
edubridge-learning-resources     (courses, materials)
edubridge-application-documents  (university applications)
edubridge-reports                (generated reports)
edubridge-temporary-uploads      (temp files)
edubridge-media-assets           (images, videos)
edubridge-generated-files        (auto-generated docs)
```

### File Lifecycle

```
1. Application receives upload
2. Validation layer checks file
3. Virus scanning (future)
4. Metadata validation
5. MinIO stores binary
6. Metadata created in PostgreSQL
7. Audit log recorded
8. Monitoring tracks operation
```

### Security Requirements

- No public buckets by default
- No permanent download URLs
- Temporary presigned URLs with expiration
- File size limits enforced
- Upload validation required
- Access level controls (PRIVATE, RESTRICTED, MANAGED, POLICY_CONTROLLED)

### Versioning Requirements

- Track file versions in PostgreSQL
- MinIO supports versioned objects
- Previous versions recoverable
- Retention policies configurable

### Monitoring Requirements

- Storage usage metrics
- Upload/download statistics
- Validation statistics
- Version statistics
- Performance metrics
- Health checks

## Consequences

### Benefits

1. Clear separation of concerns
2. PostgreSQL remains source of truth
3. MinIO optimized for binary storage
4. Metadata queryable via SQL
5. Audit trail maintained
6. Security enforced at application layer
7. Scalable independently

### Trade-offs

1. Two systems to maintain
2. Distributed transactions not possible
3. Eventual consistency for metadata sync
4. More complex integration layer

## Scope

**Permanent** - This decision applies to all object storage in EduBridge OS.

## Related Decisions

- ADR-006: PostgreSQL Schema-Based Modular Database Architecture
- ADR-007: Redis Performance-Only Caching Architecture
- ADR-008: Kafka Event-Driven Architecture
