package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostRequestBody;

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
