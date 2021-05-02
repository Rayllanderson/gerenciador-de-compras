package com.rayllanderson.gerenciadordecompras.domain.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Modelo usado para enviar informações dos produtos ao client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticResponseBody {

    private boolean completed;
    @Builder.Default
    private Integer numberOfProducts = 0;
    @Builder.Default
    private Integer numberOfProductsPurchased = 0;
    @Builder.Default
    private Integer numberOfProductsNotPurchased = 0;
    @Builder.Default
    private BigDecimal totalStipulated = BigDecimal.ZERO;

    private String categoryName;

    @Builder.Default
    private BigDecimal categoryBudget  = BigDecimal.ZERO;

    /**
     * Soma dos valores gastos até o momento.
     */
    @Builder.Default
    private BigDecimal currentAmountSpent = BigDecimal.ZERO;

    /**
     * Soma dos valores gastos com os valores estipulados. Ou seja, se um produto foi comprado, considera o valor gasto, senão,
     * considera o valor estipulado.
     */
    @Builder.Default
    private BigDecimal currentAmountTotal = BigDecimal.ZERO;

    /**
     * Soma dos valores estipulados dos produtos que não foram comprados
     */
    @Builder.Default
    private BigDecimal currentAmountStipulated = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal amountSaved = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal availableToSpend = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal availableToSpendIfBuyAll = BigDecimal.ZERO;

}
