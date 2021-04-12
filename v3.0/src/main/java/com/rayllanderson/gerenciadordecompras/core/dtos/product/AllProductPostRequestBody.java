package com.rayllanderson.gerenciadordecompras.core.dtos.product;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AllProductPostRequestBody extends ProductDtoModel {
    private Long categoryId;
}
