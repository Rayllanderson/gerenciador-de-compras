package com.rayllanderson.gerenciadordecompras.core.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comumente usado pra duplicar categorias
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferCategoryRequestBody {
    private Long id;
    private String newName;
}
