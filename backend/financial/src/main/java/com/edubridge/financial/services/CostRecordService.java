package com.edubridge.financial.services;

import com.edubridge.financial.entities.CostRecord;
import com.edubridge.financial.repositories.CostRecordRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostRecordService {

    private final CostRecordRepository costRecordRepository;

    @Transactional
    public CostRecord createCostRecord(CostRecord costRecord) {
        CostRecord saved = costRecordRepository.save(costRecord);
        log.info("Cost record created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public CostRecord getCostRecordById(String id) {
        return costRecordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cost record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CostRecord> getCostRecordsByUniversity(String universityId) {
        return costRecordRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<CostRecord> getCostRecordsByProgram(String programId) {
        return costRecordRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<CostRecord> getCostRecordsByCountry(String countryCode) {
        return costRecordRepository.findByCountryCode(countryCode);
    }

    @Transactional(readOnly = true)
    public List<CostRecord> getCostRecordsByType(String costType) {
        return costRecordRepository.findByCostType(costType);
    }

    @Transactional(readOnly = true)
    public List<CostRecord> getActiveCostRecords() {
        return costRecordRepository.findByIsActiveTrue();
    }

    @Transactional
    public CostRecord updateCostRecord(String id, CostRecord costRecord) {
        CostRecord existing = getCostRecordById(id);

        existing.setCostType(costRecord.getCostType());
        existing.setName(costRecord.getName());
        existing.setDescription(costRecord.getDescription());
        existing.setAmount(costRecord.getAmount());
        existing.setCurrencyCode(costRecord.getCurrencyCode());
        existing.setFrequency(costRecord.getFrequency());
        existing.setAcademicYear(costRecord.getAcademicYear());
        existing.setIsMandatory(costRecord.getIsMandatory());
        existing.setIsEstimated(costRecord.getIsEstimated());
        existing.setSource(costRecord.getSource());

        CostRecord saved = costRecordRepository.save(existing);
        log.info("Cost record updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public CostRecord deactivateCostRecord(String id) {
        CostRecord existing = getCostRecordById(id);
        existing.setIsActive(false);
        CostRecord saved = costRecordRepository.save(existing);
        log.info("Cost record deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public CostRecord activateCostRecord(String id) {
        CostRecord existing = getCostRecordById(id);
        existing.setIsActive(true);
        CostRecord saved = costRecordRepository.save(existing);
        log.info("Cost record activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
