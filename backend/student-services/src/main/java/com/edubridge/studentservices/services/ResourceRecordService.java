package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.ResourceRecord;
import com.edubridge.studentservices.repositories.ResourceRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceRecordService {

    private final ResourceRecordRepository resourceRecordRepository;

    @Transactional
    public ResourceRecord createResourceRecord(ResourceRecord record) {
        log.info("Creating resource record for student: {}", record.getStudentId());
        return resourceRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public ResourceRecord getResourceRecordById(String id) {
        return resourceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ResourceRecord> getResourceRecordsByProfile(String supportProfileId) {
        return resourceRecordRepository.findBySupportProfileIdAndIsActiveTrue(supportProfileId);
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
    public List<ResourceRecord> getResourceRecordsByStatus(String resourceStatus) {
        return resourceRecordRepository.findByResourceStatusAndIsActiveTrue(resourceStatus);
    }

    @Transactional(readOnly = true)
    public List<ResourceRecord> getActiveByStudent(String studentId) {
        return resourceRecordRepository.findActiveByStudent(studentId);
    }

    @Transactional
    public ResourceRecord updateResourceRecord(String id, ResourceRecord updated) {
        ResourceRecord existing = getResourceRecordById(id);
        existing.setResourceType(updated.getResourceType());
        existing.setResourceName(updated.getResourceName());
        existing.setResourceStatus(updated.getResourceStatus());
        existing.setDescription(updated.getDescription());
        existing.setProvider(updated.getProvider());
        existing.setAllocatedDate(updated.getAllocatedDate());
        existing.setExpiryDate(updated.getExpiryDate());
        existing.setUtilizationRate(updated.getUtilizationRate());
        existing.setSatisfactionScore(updated.getSatisfactionScore());
        existing.setNotes(updated.getNotes());
        return resourceRecordRepository.save(existing);
    }

    @Transactional
    public ResourceRecord returnResource(String id) {
        ResourceRecord existing = getResourceRecordById(id);
        existing.setResourceStatus("RETURNED");
        return resourceRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateResourceRecord(String id) {
        ResourceRecord existing = getResourceRecordById(id);
        existing.setActive(false);
        resourceRecordRepository.save(existing);
    }
}
