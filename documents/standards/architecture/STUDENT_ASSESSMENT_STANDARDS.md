# Student Assessment Standards

## Overview

Standards for the Student Services Domain — Student Assessment Module.

## Module Structure

```
student-services/
  Student Assessment Module
    entities/
      AssessmentProfile.java
      PerformanceRecord.java
      EvaluationRecord.java
      FeedbackRecord.java
      CompetencyRecord.java
      LifecycleRecord.java
    repositories/
      AssessmentProfileRepository.java
      PerformanceRecordRepository.java
      EvaluationRecordRepository.java
      FeedbackRecordRepository.java
      CompetencyRecordRepository.java
      LifecycleRecordRepository.java
    services/
      AssessmentProfileService.java
      PerformanceRecordService.java
      EvaluationRecordService.java
      FeedbackRecordService.java
      CompetencyRecordService.java
      LifecycleRecordService.java
    dto/
      AssessmentProfileResponse.java
      PerformanceRecordResponse.java
      EvaluationRecordResponse.java
      FeedbackRecordResponse.java
      CompetencyRecordResponse.java
      LifecycleRecordResponse.java
    controllers/
      StudentAssessmentController.java
```

## Standards

### Entities

- Extend `AuditableEntity`
- Use schema `student_services`
- Use `@Entity`, `@Table`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`
- Status enums as String columns with comments
- Soft delete via `isActive` field

### Repositories

- Extend `JpaRepository<Entity, String>`
- Use `@Repository` annotation
- Custom query methods for filtered lookups
- Use `@Query` for complex queries

### Services

- Use `@Service` and `@RequiredArgsConstructor`
- Use `@Slf4j` for logging
- Use `@Transactional` for write operations
- Use `@Transactional(readOnly = true)` for read operations

### DTOs

- Use `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`
- Response DTOs match entity fields

### Controllers

- Use `@RestController` and `@RequestMapping`
- Use `@RequiredArgsConstructor` for dependency injection
- Use `@Slf4j` for logging
- RESTful endpoints with proper HTTP methods
- Return `ResponseEntity` with proper status codes
