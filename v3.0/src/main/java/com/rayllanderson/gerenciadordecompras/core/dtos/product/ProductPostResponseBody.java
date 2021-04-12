package com.rayllanderson.gerenciadordecompras.core.dtos.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductPostResponseBody extends ProductDtoModel {
    private Long id;
}
