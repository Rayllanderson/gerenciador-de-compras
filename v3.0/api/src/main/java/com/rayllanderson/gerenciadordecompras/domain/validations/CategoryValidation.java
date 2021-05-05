package com.rayllanderson.gerenciadordecompras.domain.validations;

import com.rayllanderson.gerenciadordecompras.domain.model.Category;

import java.math.BigDecimal;

public class CategoryValidation {

    public static void validateCategoryBudget(Category category){
        if (category.getBudget() == null) category.setBudget(BigDecimal.ZERO);
    }
}
