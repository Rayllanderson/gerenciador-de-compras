package com.rayllanderson.gerenciadordecompras.domain.dtos.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class AllProductPostRequestBody extends ProductDtoModel {
    private Long categoryId;
}
