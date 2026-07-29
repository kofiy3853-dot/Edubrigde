package com.edubridge.financial.services;

import com.edubridge.financial.entities.CostItem;
import com.edubridge.financial.repositories.CostItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostItemService {

    private final CostItemRepository costItemRepository;

    @Transactional
    public CostItem createCostItem(CostItem costItem) {
        log.info("Creating cost item: {} for university: {}", costItem.getName(), costItem.getUniversityId());
        return costItemRepository.save(costItem);
    }

    @Transactional(readOnly = true)
    public CostItem getCostItemById(String id) {
        return costItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cost item not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CostItem> getCostItemsByUniversity(String universityId) {
        return costItemRepository.findByUniversityIdAndIsActiveTrue(universityId);
    }

    @Transactional(readOnly = true)
    public List<CostItem> getCostItemsByProgram(String programId) {
        return costItemRepository.findByProgramIdAndIsActiveTrue(programId);
    }

    @Transactional(readOnly = true)
    public List<CostItem> getCostItemsByCategory(String categoryCode) {
        return costItemRepository.findByCategoryCodeAndIsActiveTrue(categoryCode);
    }

    @Transactional(readOnly = true)
    public List<CostItem> getCostItemsByUniversityAndCategory(String universityId, String categoryCode) {
        return costItemRepository.findByUniversityIdAndCategoryCodeAndIsActiveTrue(universityId, categoryCode);
    }

    @Transactional(readOnly = true)
    public List<CostItem> getCostItemsByUniversityAndYear(String universityId, Integer academicYear) {
        return costItemRepository.findByUniversityIdAndAcademicYearAndIsActiveTrue(universityId, academicYear);
    }

    @Transactional(readOnly = true)
    public List<CostItem> getCostItemsByUniversityAndProgram(String universityId, String programId) {
        return costItemRepository.findByUniversityAndProgram(universityId, programId);
    }

    @Transactional(readOnly = true)
    public List<CostItem> getMandatoryCostItems(String universityId) {
        return costItemRepository.findMandatoryByUniversity(universityId);
    }

    @Transactional(readOnly = true)
    public List<CostItem> getOptionalCostItems(String universityId) {
        return costItemRepository.findOptionalByUniversity(universityId);
    }

    @Transactional
    public CostItem updateCostItem(String id, CostItem updatedItem) {
        CostItem existing = getCostItemById(id);
        existing.setName(updatedItem.getName());
        existing.setDescription(updatedItem.getDescription());
        existing.setAmount(updatedItem.getAmount());
        existing.setCurrencyCode(updatedItem.getCurrencyCode());
        existing.setFrequency(updatedItem.getFrequency());
        existing.setAcademicYear(updatedItem.getAcademicYear());
        existing.setMandatory(updatedItem.getIsMandatory());
        existing.setEstimated(updatedItem.getIsEstimated());
        existing.setRefundable(updatedItem.getIsRefundable());
        existing.setRefundPolicy(updatedItem.getRefundPolicy());
        existing.setSource(updatedItem.getSource());
        existing.setEffectiveFrom(updatedItem.getEffectiveFrom());
        existing.setEffectiveTo(updatedItem.getEffectiveTo());
        existing.setActive(updatedItem.getIsActive());
        return costItemRepository.save(existing);
    }

    @Transactional
    public void deactivateCostItem(String id) {
        CostItem existing = getCostItemById(id);
        existing.setActive(false);
        costItemRepository.save(existing);
    }
}
