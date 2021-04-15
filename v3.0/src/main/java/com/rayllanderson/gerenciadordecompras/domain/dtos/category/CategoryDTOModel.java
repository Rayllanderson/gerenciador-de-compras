package com.rayllanderson.gerenciadordecompras.domain.dtos.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class CategoryDTOModel {

    @Size(min = 1, max = 50)
    private String name;

    @DecimalMax("999999")
    private BigDecimal budget;
}
