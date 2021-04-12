package com.rayllanderson.gerenciadordecompras.utils.product;

import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPutRequestBody;

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
