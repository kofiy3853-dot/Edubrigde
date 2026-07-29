package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.ResourceRecord;
import com.edubridge.learningservices.repositories.ResourceRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceRecordService {

    private final ResourceRecordRepository resourceRecordRepository;

    @Transactional
    public ResourceRecord createResourceRecord(ResourceRecord record) {
        log.info("Creating resource record for student: {} resource: {}", record.getStudentId(), record.getResourceName());
        return resourceRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public ResourceRecord getResourceRecordById(String id) {
        return resourceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ResourceRecord> getResourceRecordsByCourse(String courseProfileId) {
        return resourceRecordRepository.findByCourseProfileIdAndIsActiveTrue(courseProfileId);
    }

    @Transactional(readOnly = true)
    public List<ResourceRecord> getResourceRecordsByStudent(String studentId) {
        return resourceRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<ResourceRecord> getResourceRecordsByType(String resourceType) {
        return resourceRecordRepository.findByResourceTypeAndIsActiveTrue(resourceType);
    }

    @Transactional(readOnly = true)
    public List<ResourceRecord> getAccessibleByStudent(String studentId) {
        return resourceRecordRepository.findAccessibleByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<ResourceRecord> getCompletedByStudent(String studentId) {
        return resourceRecordRepository.findCompletedByStudent(studentId);
    }

    @Transactional
    public ResourceRecord updateResourceRecord(String id, ResourceRecord updated) {
        ResourceRecord existing = getResourceRecordById(id);
        existing.setResourceType(updated.getResourceType());
        existing.setResourceName(updated.getResourceName());
        existing.setResourceStatus(updated.getResourceStatus());
        existing.setDescription(updated.getDescription());
        existing.setResourceUrl(updated.getResourceUrl());
        existing.setFormatType(updated.getFormatType());
        return resourceRecordRepository.save(existing);
    }

    @Transactional
    public ResourceRecord accessResource(String id) {
        ResourceRecord existing = getResourceRecordById(id);
        existing.setAccessCount(existing.getAccessCount() + 1);
        existing.setLastAccessedAt(LocalDateTime.now());
        return resourceRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateResourceRecord(String id) {
        ResourceRecord existing = getResourceRecordById(id);
        existing.setActive(false);
        resourceRecordRepository.save(existing);
    }
}
