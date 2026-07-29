# MinIO Object Storage Standards

## LOCKED STANDARDS

These standards are permanently locked and cannot be modified.

---

## 1. Metadata-First Architecture

```
MinIO stores: Binary files ONLY
PostgreSQL stores: ALL metadata, ownership, permissions, audit
```

MinIO must never become permanent metadata storage.

---

## 2. Bucket Standards

### Required Buckets

| Bucket | Purpose |
|--------|---------|
| edubridge-student-documents | Student uploads |
| edubridge-student-certificates | Certificates |
| edubridge-learning-resources | Course materials |
| edubridge-application-documents | University applications |
| edubridge-reports | Generated reports |
| edubridge-temporary-uploads | Temporary files |
| edubridge-media-assets | Images, videos |
| edubridge-generated-files | Auto-generated documents |

### Bucket Rules

- Names prefixed with `edubridge-`
- Each bucket isolated by responsibility
- No shared buckets
- No public buckets by default

---

## 3. File Validation Standards

Every upload must pass:

- File type validation
- File size validation (max 50MB)
- Malware detection (future)
- Metadata validation
- Ownership validation
- Access validation

---

## 4. Versioning Standards

- Track all file versions in PostgreSQL
- MinIO versioning enabled by default
- Previous versions recoverable
- Retention policies configurable

---

## 5. Security Standards

### Access Levels

| Level | Description |
|-------|-------------|
| PRIVATE | Owner only |
| RESTRICTED | Specific users/roles |
| MANAGED | Policy-controlled |
| POLICY_CONTROLLED | Complex rules |

### Prohibited

- Public buckets by default
- Permanent download URLs
- Unlimited file sizes
- Unvalidated uploads

### Required

- Temporary presigned URLs
- Expiration on all URLs
- Access logging
- Encryption support

---

## 6. Temporary URL Standards

- Default expiration: 15 minutes
- Maximum expiration: 24 hours
- Revocation support required
- Monitoring required

---

## 7. Retention Standards

- Temporary files: configurable expiration
- Student documents: configurable retention
- Reports: configurable retention
- Audit requirements: preservation policies

---

## 8. Monitoring Standards

Track:

- Storage usage
- Upload statistics
- Download statistics
- Validation statistics
- Version statistics
- Performance metrics
- Health checks

---

## 9. Testing Standards

Required tests:

- Upload tests
- Download tests
- Validation tests
- Security tests
- Version tests
- Performance tests
- Integration tests

---

## 10. Object Storage Structure

```
infrastructure/
  minio/
    configurations/
    policies/
    documentation/
    monitoring/
    testing/

backend/
  shared/
    storage/
      minio/        (MinIO client, config, services)
      upload/       (upload services)
      download/     (download services)
      validation/   (file validation)
      metadata/     (metadata records)
```
