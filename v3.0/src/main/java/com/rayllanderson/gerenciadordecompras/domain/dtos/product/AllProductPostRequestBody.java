package com.rayllanderson.gerenciadordecompras.domain.dtos.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AllProductPostRequestBody extends ProductDtoModel {
    @NotEmpty
    private Long categoryId;
}
