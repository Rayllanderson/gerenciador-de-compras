package com.rayllanderson.gerenciadordecompras.core.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostRequestBody {

    private String name;
    private BigDecimal stipulatedPrice;
    private BigDecimal spentPrice;
    private Boolean purchased;

}
