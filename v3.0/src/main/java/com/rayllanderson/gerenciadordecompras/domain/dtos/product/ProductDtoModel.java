package com.rayllanderson.gerenciadordecompras.domain.dtos.product;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Data
@SuperBuilder
@NoArgsConstructor
public abstract class ProductDtoModel {

    @Size(min = 1, max = 50)
    protected String name;

    @DecimalMax("999999")
    protected BigDecimal stipulatedPrice;

    @DecimalMax("999999")
    protected BigDecimal spentPrice;

    protected Boolean purchased;
}
