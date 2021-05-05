package com.rayllanderson.gerenciadordecompras.domain.services;

import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.utils.StatisticUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatisticService {

    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * Recupera as estatísticas de uma categoria específica.
     * @param categoryId categoria a ser verificada.
     * @param userId necessário para validar o user.
     * @return as estatísticas da categoria ou null a categoria não exista.
     */
    @Transactional(readOnly = true)
    public StatisticResponseBody getStatistics(Long categoryId, Long userId) {
        Category category = categoryService.findById(categoryId, userId);
        List<Product> allProductsFromCategory = productService.findAllNonPageable(categoryId, userId);

        // recuperando valores
        BigDecimal currentAmountToSpent = StatisticUtil.getCurrentAmountSpended(allProductsFromCategory);
        BigDecimal amountSaved = StatisticUtil.getAmountSaved(allProductsFromCategory);
        BigDecimal currentAmountStipulated = StatisticUtil.getCurrentAmountStipulated(allProductsFromCategory);
        BigDecimal currentAmountTotal = StatisticUtil.getCurrentAmountTotal(allProductsFromCategory);
        BigDecimal totalStipulated = StatisticUtil.getTotalStipulated(allProductsFromCategory);
        BigDecimal amountToSpend = StatisticUtil.getAmountToSpend(allProductsFromCategory);
        Integer numberOfProducts = StatisticUtil.getNumberOfProducts(allProductsFromCategory);
        Integer numberOfProductsPurchased = StatisticUtil.getNumberOfProductsPurchased(allProductsFromCategory);
        Integer numberOfProductsNotPurchased = StatisticUtil.getNumberOfProductsNotPurchased(allProductsFromCategory);

        BigDecimal availableToSpend = StatisticUtil.getAvailableToSpend(category, currentAmountToSpent);
        BigDecimal availableToSpendIfBuyAll = StatisticUtil.getAvailableToSpendIfBuyAll(availableToSpend, currentAmountStipulated);
        BigDecimal categoryBudget = StatisticUtil.getCategoryBudget(category);
        String categoryName = category.getName();
        boolean isCompleted = StatisticUtil.isCompleted(allProductsFromCategory);

        //setando os valores
        return StatisticResponseBody.builder()
                .availableToSpend(availableToSpend)
                .completed(isCompleted)
                .categoryName(categoryName)
                .categoryBudget(categoryBudget)
                .amountToSpend(amountToSpend)
                .currentAmountTotal(currentAmountTotal)
                .currentAmountSpent(currentAmountToSpent)
                .currentAmountStipulated(currentAmountStipulated)
                .availableToSpendIfBuyAll(availableToSpendIfBuyAll)
                .amountSaved(amountSaved).numberOfProducts(numberOfProducts)
                .numberOfProductsNotPurchased(numberOfProductsNotPurchased)
                .numberOfProductsPurchased(numberOfProductsPurchased)
                .totalStipulated(totalStipulated).build();
    }

    /**
     * Recupera as estatísticas de todas as categorias de um usuário
     * @return as estatísticas ou null caso o user não possuir nenhuma categoria.
     */
    @Transactional(readOnly = true)
    public StatisticResponseBody getStatistics(Long userId) {
        List<Category> categories = categoryService.findAllNonPageable(userId);
        boolean userHasAtLeastOneCategory = !categories.isEmpty();
        if (userHasAtLeastOneCategory) {
            List<StatisticResponseBody> statistics = new ArrayList<>();
            categories.forEach(category -> statistics.add(this.getStatistics(category.getId(), userId)));
            StatisticResponseBody data = new StatisticResponseBody();
            statistics.forEach(s -> {
                data.setAmountSaved(data.getAmountSaved().add(s.getAmountSaved()));
                data.setAvailableToSpend(data.getAvailableToSpend().add(s.getAvailableToSpend()));
                data.setAvailableToSpendIfBuyAll(data.getAvailableToSpendIfBuyAll().add(s.getAvailableToSpendIfBuyAll()));
                data.setCurrentAmountStipulated(data.getCurrentAmountStipulated().add(s.getCurrentAmountStipulated()));
                data.setCurrentAmountSpent(data.getCurrentAmountSpent().add(s.getCurrentAmountSpent()));
                data.setCurrentAmountTotal(data.getCurrentAmountTotal().add(s.getCurrentAmountTotal()));
                data.setNumberOfProducts(data.getNumberOfProducts() + s.getNumberOfProducts());
                data.setNumberOfProductsPurchased(data.getNumberOfProductsPurchased() + s.getNumberOfProductsPurchased());
                data.setNumberOfProductsNotPurchased(data.getNumberOfProductsNotPurchased() + s.getNumberOfProductsNotPurchased());
                data.setTotalStipulated(data.getTotalStipulated().add(s.getTotalStipulated()));
            });
            return data;
        }else return null;
    }
}
