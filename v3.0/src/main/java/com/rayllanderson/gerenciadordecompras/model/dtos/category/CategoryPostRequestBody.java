package com.rayllanderson.gerenciadordecompras.model.dtos.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPostRequestBody {

    private String name;
    private BigDecimal budget;

}
