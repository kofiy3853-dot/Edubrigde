package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.GrowthLifecycleRecord;
import com.edubridge.learningservices.repositories.GrowthLifecycleRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GrowthLifecycleRecordService {

    private final GrowthLifecycleRecordRepository growthLifecycleRecordRepository;

    @Transactional
    public GrowthLifecycleRecord createGrowthLifecycleRecord(GrowthLifecycleRecord record) {
        log.info("Creating growth lifecycle record for student: {} entity: {}", record.getStudentId(), record.getEntityType());
        record.setChangedAt(LocalDateTime.now());
        return growthLifecycleRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public GrowthLifecycleRecord getGrowthLifecycleRecordById(String id) {
        return growthLifecycleRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Growth lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<GrowthLifecycleRecord> getGrowthLifecycleRecordsByGrowthProfile(String growthProfileId) {
        return growthLifecycleRecordRepository.findByGrowthProfileIdAndIsActiveTrue(growthProfileId);
    }

    @Transactional(readOnly = true)
    public List<GrowthLifecycleRecord> getGrowthLifecycleRecordsByStudent(String studentId) {
        return growthLifecycleRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<GrowthLifecycleRecord> getGrowthLifecycleRecordsByEntityType(String entityType) {
        return growthLifecycleRecordRepository.findByEntityTypeAndIsActiveTrue(entityType);
    }

    @Transactional(readOnly = true)
    public List<GrowthLifecycleRecord> getGrowthLifecycleRecordsByStatus(String newStatus) {
        return growthLifecycleRecordRepository.findByNewStatusAndIsActiveTrue(newStatus);
    }

    @Transactional(readOnly = true)
    public List<GrowthLifecycleRecord> getGrowthLifecycleByEntityTypeAndEntityId(String entityType, String entityId) {
        return growthLifecycleRecordRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    @Transactional(readOnly = true)
    public List<GrowthLifecycleRecord> getRecentByStudent(String studentId) {
        return growthLifecycleRecordRepository.findRecentByStudent(studentId);
    }

    @Transactional
    public void deactivateGrowthLifecycleRecord(String id) {
        GrowthLifecycleRecord existing = getGrowthLifecycleRecordById(id);
        existing.setActive(false);
        growthLifecycleRecordRepository.save(existing);
    }
}
