package com.rayllanderson.gerenciadordecompras.core.requests;

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
public class StatisticsResponseBody {

    private boolean completed;
    private Integer numberOfProducts;
    private Integer numberOfProductsPurchased;
    private Integer numberOfProductsNotPurchased;
    private BigDecimal totalStipulated;
    private BigDecimal categoryBudget;

    /**
     * Soma dos valores gastos até o momento.
     */
    private BigDecimal currentAmountToSpent;

    /**
     * Soma dos valores gastos com os valores estipulados. Ou seja, se um produto foi comprado, considera o valor gasto, senão,
     * considera o valor estipulado.
     */
    private BigDecimal currentAmountTotal;

    /**
     * Soma dos valores estipulados dos produtos que não foram comprados
     */
    private BigDecimal currentAmountStipulated;
    private BigDecimal amountSaved;
    private BigDecimal availableToSpend;
    private BigDecimal availableToSpendIfBuyAll;

}
