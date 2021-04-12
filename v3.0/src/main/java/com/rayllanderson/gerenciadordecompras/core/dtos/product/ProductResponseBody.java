package com.rayllanderson.gerenciadordecompras.core.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductResponseBody extends ProductDtoModel{
    private Long id;
}
