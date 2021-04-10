package com.rayllanderson.gerenciadordecompras.core.services;

import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import com.rayllanderson.gerenciadordecompras.core.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.core.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.core.requests.StatisticsResponseBody;
import com.rayllanderson.gerenciadordecompras.core.services.utils.StatisticsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatisticService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public StatisticsResponseBody getStatistics(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            List<Product> allProductsFromCategory = productRepository.findAllByCategoryId(categoryId);
            // recuperando valores
            BigDecimal currentAmountToSpent = StatisticsUtil.getCurrentAmountToSpent(allProductsFromCategory);
            BigDecimal amountSaved = StatisticsUtil.getAmountSaved(allProductsFromCategory);
            BigDecimal currentAmountStipulated = StatisticsUtil.getCurrentAmountStipulated(allProductsFromCategory);
            BigDecimal currentAmountTotal = StatisticsUtil.getCurrentAmountTotal(allProductsFromCategory);
            BigDecimal totalStipulated = StatisticsUtil.getTotalStipulated(allProductsFromCategory);
            Integer numberOfProducts = StatisticsUtil.getNumberOfProducts(allProductsFromCategory);
            Integer numberOfProductsPurchased = StatisticsUtil.getNumberOfProductsPurchased(allProductsFromCategory);
            Integer numberOfProductsNotPurchased = StatisticsUtil.getNumberOfProductsNotPurchased(allProductsFromCategory);
            BigDecimal availableToSpend = StatisticsUtil.getAvailableToSpend(category.get(), currentAmountToSpent);
            BigDecimal availableToSpendIfBuyAll = StatisticsUtil.getAvailableToSpendIfBuyAll(availableToSpend, currentAmountStipulated);
            BigDecimal categoryBudget = StatisticsUtil.getCategoryBudget(category.get());
            boolean isCompleted = StatisticsUtil.isCompleted(allProductsFromCategory);

            //setando os valores
            return StatisticsResponseBody.builder().availableToSpend(availableToSpend)
                    .completed(isCompleted)
                    .categoryBudget(categoryBudget)
                    .currentAmountTotal(currentAmountTotal)
                    .currentAmountToSpent(currentAmountToSpent)
                    .currentAmountStipulated(currentAmountStipulated)
                    .availableToSpendIfBuyAll(availableToSpendIfBuyAll)
                    .amountSaved(amountSaved).numberOfProducts(numberOfProducts)
                    .numberOfProductsNotPurchased(numberOfProductsNotPurchased)
                    .numberOfProductsPurchased(numberOfProductsPurchased)
                    .totalStipulated(totalStipulated).build();
        }
        return null;
    }

}
