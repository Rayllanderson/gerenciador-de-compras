package com.rayllanderson.gerenciadordecompras.domain.dtos.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class CategoryPutRequestBody extends CategoryDTOModel{

    @NotEmpty
    private Long id;

}
