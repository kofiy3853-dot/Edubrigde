# Student Support Standards

## Overview

Standards for the Student Services Domain — Student Support Module.

## Module Structure

```
student-services/
  Student Support Module
    entities/
      StudentSupportProfile.java
      AcademicSupportRecord.java
      WellbeingRecord.java
      ResourceRecord.java
      AssessmentRecord.java
      LifecycleRecord.java
    repositories/
      StudentSupportProfileRepository.java
      AcademicSupportRecordRepository.java
      WellbeingRecordRepository.java
      ResourceRecordRepository.java
      AssessmentRecordRepository.java
      LifecycleRecordRepository.java
    services/
      StudentSupportProfileService.java
      AcademicSupportRecordService.java
      WellbeingRecordService.java
      ResourceRecordService.java
      AssessmentRecordService.java
      LifecycleRecordService.java
    dto/
      StudentSupportProfileResponse.java
      AcademicSupportRecordResponse.java
      WellbeingRecordResponse.java
      ResourceRecordResponse.java
      AssessmentRecordResponse.java
      LifecycleRecordResponse.java
    controllers/
      StudentSupportController.java
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
