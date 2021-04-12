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
    private Integer numberOfProducts = 0;
    private Integer numberOfProductsPurchased = 0;
    private Integer numberOfProductsNotPurchased = 0;
    private BigDecimal totalStipulated = BigDecimal.ZERO;
    private BigDecimal categoryBudget  = BigDecimal.ZERO;

    /**
     * Soma dos valores gastos até o momento.
     */
    private BigDecimal currentAmountToSpent = BigDecimal.ZERO;

    /**
     * Soma dos valores gastos com os valores estipulados. Ou seja, se um produto foi comprado, considera o valor gasto, senão,
     * considera o valor estipulado.
     */
    private BigDecimal currentAmountTotal = BigDecimal.ZERO;

    /**
     * Soma dos valores estipulados dos produtos que não foram comprados
     */
    private BigDecimal currentAmountStipulated = BigDecimal.ZERO;
    private BigDecimal amountSaved = BigDecimal.ZERO;
    private BigDecimal availableToSpend = BigDecimal.ZERO;
    private BigDecimal availableToSpendIfBuyAll = BigDecimal.ZERO;

}
