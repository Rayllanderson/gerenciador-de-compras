package com.rayllanderson.gerenciadordecompras.domain.requests.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Comumente usado pra duplicar categorias
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferCategoryRequestBody {

    @NotEmpty
    @NotNull
    private Long id;

    @Size(min = 1, max = 50)
    private String newName;
}
