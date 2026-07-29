# Mentorship Services Standards

## Overview

Standards for the Student Services Domain — Mentorship Services Module.

## Module Structure

```
student-services/
  Mentorship Profile Module
    entities/
      MentorshipProfile.java
      MatchingRecord.java
      MentorProfile.java
      GuidanceRecord.java
      GrowthRecord.java
      LifecycleRecord.java
    repositories/
      MentorshipProfileRepository.java
      MatchingRecordRepository.java
      MentorProfileRepository.java
      GuidanceRecordRepository.java
      GrowthRecordRepository.java
      LifecycleRecordRepository.java
    services/
      MentorshipProfileService.java
      MatchingRecordService.java
      MentorProfileService.java
      GuidanceRecordService.java
      GrowthRecordService.java
      LifecycleRecordService.java
    dto/
      MentorshipProfileResponse.java
      MatchingRecordResponse.java
      MentorProfileResponse.java
      GuidanceRecordResponse.java
      GrowthRecordResponse.java
      LifecycleRecordResponse.java
    controllers/
      MentorshipManagementController.java
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
