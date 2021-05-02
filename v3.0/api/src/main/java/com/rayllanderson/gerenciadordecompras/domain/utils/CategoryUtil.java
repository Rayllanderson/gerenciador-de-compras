package com.rayllanderson.gerenciadordecompras.domain.utils;

import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CategoryUtil {

    private final ProductRepository productRepository;

    public CategoryUtil(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setCompletedPercentage(List<Category> categories) {
        categories.forEach(category -> {
            Long categoryId = category.getId();
            Long userId = category.getUser().getId();
            int numberOfCompletedProducts = productRepository.countProductPurchased(categoryId, userId);
            boolean hasNoProductsInThisCategory = numberOfCompletedProducts == 0;
            if (hasNoProductsInThisCategory) {
                category.setCompletedPercentage(BigDecimal.ZERO);
                return;
            }
            int numberOfTotalProducts = productRepository.countProductByCategoryIdAndCategoryUserId(categoryId, userId);
            BigDecimal percentage = new BigDecimal((numberOfCompletedProducts * 100) / numberOfTotalProducts);
            percentage = percentage.setScale(2, RoundingMode.HALF_EVEN);
            category.setCompletedPercentage(percentage);
        });
    }
}
