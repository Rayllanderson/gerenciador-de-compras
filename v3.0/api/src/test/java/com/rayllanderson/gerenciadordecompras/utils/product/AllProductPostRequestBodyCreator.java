package com.rayllanderson.gerenciadordecompras.utils.product;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.AllProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;

import java.math.BigDecimal;

public class AllProductPostRequestBodyCreator {

    public static AllProductPostRequestBody createAllProductPostRequestBody(){
        return AllProductPostRequestBody.builder().
                name(ProductCreator.createProductWithId().getName())
                .purchased(true)
                .spentPrice(new BigDecimal("399.95"))
                .stipulatedPrice(new BigDecimal("599.85"))
                .categoryId(CategoryCreator.createCategoryWithId().getId())
                .build();
    }
}
