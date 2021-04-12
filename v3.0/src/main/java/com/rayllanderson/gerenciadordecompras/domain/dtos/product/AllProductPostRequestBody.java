package com.rayllanderson.gerenciadordecompras.domain.dtos.product;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AllProductPostRequestBody extends ProductDtoModel {
    private Long categoryId;
}
