package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.CareerLifecycleRecord;
import com.edubridge.learningservices.repositories.CareerLifecycleRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CareerLifecycleRecordService {

    private final CareerLifecycleRecordRepository careerLifecycleRecordRepository;

    @Transactional
    public CareerLifecycleRecord createCareerLifecycleRecord(CareerLifecycleRecord record) {
        log.info("Creating career lifecycle record for student: {} entity: {}", record.getStudentId(), record.getEntityType());
        record.setChangedAt(LocalDateTime.now());
        return careerLifecycleRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public CareerLifecycleRecord getCareerLifecycleRecordById(String id) {
        return careerLifecycleRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CareerLifecycleRecord> getCareerLifecycleRecordsByCareerProfile(String careerProfileId) {
        return careerLifecycleRecordRepository.findByCareerProfileIdAndIsActiveTrue(careerProfileId);
    }

    @Transactional(readOnly = true)
    public List<CareerLifecycleRecord> getCareerLifecycleRecordsByStudent(String studentId) {
        return careerLifecycleRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<CareerLifecycleRecord> getCareerLifecycleRecordsByEntityType(String entityType) {
        return careerLifecycleRecordRepository.findByEntityTypeAndIsActiveTrue(entityType);
    }

    @Transactional(readOnly = true)
    public List<CareerLifecycleRecord> getCareerLifecycleRecordsByStatus(String newStatus) {
        return careerLifecycleRecordRepository.findByNewStatusAndIsActiveTrue(newStatus);
    }

    @Transactional(readOnly = true)
    public List<CareerLifecycleRecord> getCareerLifecycleByEntityTypeAndEntityId(String entityType, String entityId) {
        return careerLifecycleRecordRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    @Transactional(readOnly = true)
    public List<CareerLifecycleRecord> getRecentByStudent(String studentId) {
        return careerLifecycleRecordRepository.findRecentByStudent(studentId);
    }

    @Transactional
    public void deactivateCareerLifecycleRecord(String id) {
        CareerLifecycleRecord existing = getCareerLifecycleRecordById(id);
        existing.setActive(false);
        careerLifecycleRecordRepository.save(existing);
    }
}
