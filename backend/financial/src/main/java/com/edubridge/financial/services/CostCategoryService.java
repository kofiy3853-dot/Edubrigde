package com.edubridge.financial.services;

import com.edubridge.financial.entities.CostCategory;
import com.edubridge.financial.repositories.CostCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostCategoryService {

    private final CostCategoryRepository costCategoryRepository;

    @Transactional
    public CostCategory createCategory(CostCategory category) {
        log.info("Creating cost category: {}", category.getCode());
        if (costCategoryRepository.existsByCode(category.getCode())) {
            throw new RuntimeException("Category with code " + category.getCode() + " already exists");
        }
        return costCategoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public CostCategory getCategoryById(String id) {
        return costCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cost category not found: " + id));
    }

    @Transactional(readOnly = true)
    public CostCategory getCategoryByCode(String code) {
        return costCategoryRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Cost category not found: " + code));
    }

    @Transactional(readOnly = true)
    public List<CostCategory> getAllActiveCategories() {
        return costCategoryRepository.findByIsActiveTrueOrderBySortOrder();
    }

    @Transactional(readOnly = true)
    public List<CostCategory> getRootCategories() {
        return costCategoryRepository.findRootCategories();
    }

    @Transactional(readOnly = true)
    public List<CostCategory> getSubCategories(String parentCode) {
        return costCategoryRepository.findByParentCode(parentCode);
    }

    @Transactional
    public CostCategory updateCategory(String id, CostCategory updatedCategory) {
        CostCategory existing = getCategoryById(id);
        existing.setName(updatedCategory.getName());
        existing.setDescription(updatedCategory.getDescription());
        existing.setParentCode(updatedCategory.getParentCode());
        existing.setSortOrder(updatedCategory.getSortOrder());
        existing.setActive(updatedCategory.getIsActive());
        return costCategoryRepository.save(existing);
    }

    @Transactional
    public void deactivateCategory(String id) {
        CostCategory existing = getCategoryById(id);
        existing.setActive(false);
        costCategoryRepository.save(existing);
    }
}
