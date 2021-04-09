package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import com.rayllanderson.gerenciadordecompras.model.entities.Product;

import java.math.BigDecimal;

public class ProductPostRequestBodyCreator {

    public static ProductPostRequestBody createProductPostRequestBody(){
        return ProductPostRequestBody.builder().
                name("Gabinete")
                .purchased(true)
                .spentPrice(new BigDecimal("399.95"))
                .stipulatedPrice(new BigDecimal("599.85"))
                .build();
    }
}
