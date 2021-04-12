package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPutRequestBody;

import java.math.BigDecimal;

public class CategoryPutRequestBodyCreator {

    public static CategoryPutRequestBody createCategoryPutRequestBody(){
        return CategoryPutRequestBody.builder()
                .id(1L)
                .name("Compras")
                .budget(new BigDecimal(100))
                .build();
    }
}
