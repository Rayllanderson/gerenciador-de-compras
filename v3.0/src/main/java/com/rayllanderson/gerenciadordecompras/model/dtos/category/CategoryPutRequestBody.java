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
public class CategoryPutRequestBody {

    private Long id;
    private String name;
    private BigDecimal estimation;

}
