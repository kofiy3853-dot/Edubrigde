# MinIO Responsibilities

## Status

LOCKED - Sprint 0B, Step 08

## Principle

MinIO manages all file storage. Files never go in PostgreSQL.

## Responsibilities

### 1. Student Documents

```
Types:
  - Passport scans
  - Academic transcripts
  - Recommendation letters
  - Financial documents

Storage:  students/{userId}/documents/
Retention: Permanent (with soft delete)
```

### 2. Certificates

```
Types:
  - Completion certificates
  - Achievement certificates
  - Skill certifications

Storage:  certificates/{userId}/
Retention: Permanent
```

### 3. Assignments

```
Types:
  - Student submissions
  - Graded assignments
  - Project files

Storage:  assignments/{courseId}/{assignmentId}/
Retention: Course duration + 1 year
```

### 4. Reports

```
Types:
  - Academic transcripts
  - Financial statements
  - Analytics reports

Storage:  reports/{type}/{userId}/
Retention: As per policy
```

### 5. Images

```
Types:
  - Profile pictures
  - University logos
  - Course thumbnails

Storage:  images/{type}/{id}.ext
Retention: Permanent
```

### 6. Future: Videos

```
Types:
  - Course videos
  - Tutorial recordings
  - Webinar recordings

Storage:  videos/{courseId}/
Retention: Course duration
```

### 7. Future: Learning Resources

```
Types:
  - PDF documents
  - Presentations
  - Code samples

Storage:  resources/{courseId}/
Retention: Course duration
```

## Bucket Structure

```
edubridge/
  +-- students/
  |     +-- {userId}/
  |           +-- documents/
  |           +-- profile/
  +-- certificates/
  |     +-- {userId}/
  +-- assignments/
  |     +-- {courseId}/
  |           +-- {assignmentId}/
  +-- reports/
  |     +-- {type}/
  |           +-- {userId}/
  +-- images/
  |     +-- profiles/
  |     +-- universities/
  |     +-- courses/
  +-- videos/ (future)
  +-- resources/ (future)
```

## File Metadata

Metadata stored in PostgreSQL:

```sql
CREATE TABLE identity.file_metadata (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    bucket VARCHAR(255) NOT NULL,
    key VARCHAR(500) NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    mime_type VARCHAR(100) NOT NULL,
    size_bytes BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP NULL
);
```

## MinIO Rules

```
1. Files accessed via presigned URLs
2. File metadata stored in PostgreSQL
3. Files organized by domain and user
4. Access controlled by IAM policies
5. Files encrypted at rest
6. Backup strategy for critical files
```

## Access Patterns

```
Upload:
  1. Client requests presigned URL
  2. Client uploads directly to MinIO
  3. Metadata saved to PostgreSQL
  4. Event published to Kafka

Download:
  1. Client requests file access
  2. Application validates permission
  3. Presigned URL generated
  4. Client downloads from MinIO
```

## What MinIO Must Never Do

```
NEVER:
  - Store application configuration
  - Store database backups
  - Store application logs
  - Be accessed directly by clients (use presigned URLs)
  - Store sensitive data without encryption
```

## Never

```
- Store files in PostgreSQL
- Allow direct client access
- Skip file validation
- Skip access control
- Skip encryption
- Skip monitoring
- Skip backup strategy
```
