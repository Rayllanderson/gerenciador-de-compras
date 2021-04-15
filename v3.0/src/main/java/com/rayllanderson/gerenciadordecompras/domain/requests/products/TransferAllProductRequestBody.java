package com.rayllanderson.gerenciadordecompras.domain.requests.products;

import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Comumente usado pra mover ou copiar produtos quando está se sendo listados todos os produtos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferAllProductRequestBody {

    @NotEmpty
    @NotNull
    private List<SelectItemsRequestBody> selectItems;

    @NotEmpty
    @NotNull
    private Long newCategoryId;
}
