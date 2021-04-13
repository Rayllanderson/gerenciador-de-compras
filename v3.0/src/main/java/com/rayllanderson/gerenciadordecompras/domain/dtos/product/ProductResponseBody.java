package com.rayllanderson.gerenciadordecompras.domain.dtos.product;

import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductResponseBody extends ProductDtoModel{
    @NotEmpty
    @NotNull
    private Long id;
    private Category category;
}
