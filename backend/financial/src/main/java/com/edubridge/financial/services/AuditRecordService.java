package com.edubridge.financial.services;

import com.edubridge.financial.entities.AuditRecord;
import com.edubridge.financial.repositories.AuditRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditRecordService {

    private final AuditRecordRepository auditRecordRepository;

    @Transactional
    public AuditRecord createAuditRecord(AuditRecord audit) {
        log.info("Creating audit record: {} for scholarship: {}", audit.getAction(), audit.getScholarshipId());
        if (audit.getPerformedAt() == null) {
            audit.setPerformedAt(LocalDateTime.now());
        }
        return auditRecordRepository.save(audit);
    }

    @Transactional(readOnly = true)
    public AuditRecord getAuditRecordById(String id) {
        return auditRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audit record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AuditRecord> getAuditRecordsByScholarship(String scholarshipId) {
        return auditRecordRepository.findByScholarshipIdAndIsActiveTrue(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<AuditRecord> getAuditRecordsByStudent(String studentId) {
        return auditRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<AuditRecord> getAuditRecordsByAction(String action) {
        return auditRecordRepository.findByActionAndIsActiveTrue(action);
    }

    @Transactional(readOnly = true)
    public List<AuditRecord> getAuditRecordsByEntityType(String entityType) {
        return auditRecordRepository.findByEntityTypeAndIsActiveTrue(entityType);
    }

    @Transactional(readOnly = true)
    public List<AuditRecord> getAuditRecordsByScholarshipAndAction(String scholarshipId, String action) {
        return auditRecordRepository.findByScholarshipAndAction(scholarshipId, action);
    }

    @Transactional(readOnly = true)
    public List<AuditRecord> getAuditRecordsByStudentAndAction(String studentId, String action) {
        return auditRecordRepository.findByStudentAndAction(studentId, action);
    }

    @Transactional(readOnly = true)
    public List<AuditRecord> getAuditRecordsByEntityTypeAndEntityId(String entityType, String entityId) {
        return auditRecordRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    @Transactional
    public void deactivateAuditRecord(String id) {
        AuditRecord existing = getAuditRecordById(id);
        existing.setActive(false);
        auditRecordRepository.save(existing);
    }
}
