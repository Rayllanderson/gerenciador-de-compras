package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Category;

import java.math.BigDecimal;

public class ProductPutRequestBodyCreator {

    public static ProductPutRequestBody createProductPutRequestBody(){
        return ProductPutRequestBody.builder()
                .id(1L)
                .name("Placa de vídeo")
                .purchased(true)
                .spentPrice(new BigDecimal("1399.95"))
                .stipulatedPrice(new BigDecimal("1599.85"))
                .build();
    }
}
