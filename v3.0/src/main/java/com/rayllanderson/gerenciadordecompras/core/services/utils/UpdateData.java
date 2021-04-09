package com.rayllanderson.gerenciadordecompras.core.services.utils;

import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import org.springframework.beans.BeanUtils;

public class UpdateData {

    /**
     * copia as propriedades de um categoryPutRequestBody para category ignorando o ID
     * @param categoryPutRequestBody contendo os novos dados
     * @param category receberá os novos dados
     */
    public static void updateCategoryData(CategoryPutRequestBody categoryPutRequestBody, Category category){
        BeanUtils.copyProperties(categoryPutRequestBody, category, "id");
    }

    public static void updateProductData(ProductPutRequestBody productPutRequestBody, Product product){
        BeanUtils.copyProperties(productPutRequestBody, product, "id");
    }
}
