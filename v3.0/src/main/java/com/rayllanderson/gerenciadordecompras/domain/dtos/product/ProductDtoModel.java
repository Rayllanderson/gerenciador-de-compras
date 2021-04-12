package com.rayllanderson.gerenciadordecompras.domain.dtos.product;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@Data
@SuperBuilder
@NoArgsConstructor
public abstract class ProductDtoModel {

    protected String name;
    protected BigDecimal stipulatedPrice;
    protected BigDecimal spentPrice;
    protected Boolean purchased;
}
