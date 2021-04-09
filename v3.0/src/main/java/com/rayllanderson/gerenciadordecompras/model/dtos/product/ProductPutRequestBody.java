package com.rayllanderson.gerenciadordecompras.model.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPutRequestBody {

    private Long id;
    private String name;
    private BigDecimal stipulatedPrice;
    private BigDecimal spentPrice;
    private Boolean purchased;

}
