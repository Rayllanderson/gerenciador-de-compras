package com.rayllanderson.gerenciadordecompras.domain.dtos.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AllProductPostRequestBody extends ProductDtoModel {
    private Long categoryId;
}
