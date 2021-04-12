package com.rayllanderson.gerenciadordecompras.core.requests.products;

import com.rayllanderson.gerenciadordecompras.core.requests.SelectItemsRequestBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Comumente usado pra mover ou copiar produtos quando está se sendo listados todos os produtos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferAllProductRequestBody {
    private List<SelectItemsRequestBody> selectItems;
    private Long newCategoryId;
}
