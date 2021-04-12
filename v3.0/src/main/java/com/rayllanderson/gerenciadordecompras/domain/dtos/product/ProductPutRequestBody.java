package com.rayllanderson.gerenciadordecompras.domain.dtos.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductPutRequestBody extends ProductDtoModel {
    private Long id;
}
