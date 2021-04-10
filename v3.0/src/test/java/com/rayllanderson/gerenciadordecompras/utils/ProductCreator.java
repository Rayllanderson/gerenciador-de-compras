package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;

import java.math.BigDecimal;

public class ProductCreator {

    public static Product createProductToBeSaved(){
        Category category = CategoryCreator.createCategoryWithId();
        return Product.builder().
                name("Gabinete")
                .category(category)
                .purchased(true)
                .spentPrice(new BigDecimal("399.95"))
                .stipulatedPrice(new BigDecimal("599.85"))
                .build();
    }

    public static Product createProductWithId(){
        Category category = CategoryCreator.createCategoryWithId();
        return Product.builder()
                .id(1L)
                .name("Gabinete")
                .category(category)
                .purchased(true)
                .spentPrice(new BigDecimal("399.95"))
                .stipulatedPrice(new BigDecimal("599.85"))
                .build();
    }

    public static Product createANonPurchasedProductWithId(){
        Category category = CategoryCreator.createCategoryWithId();
        return Product.builder()
                .id(1L)
                .name("Gabinete")
                .category(category)
                .purchased(false)
                .spentPrice(new BigDecimal("399.95"))
                .stipulatedPrice(new BigDecimal("599.85"))
                .build();
    }

    public static Product createProductToBeUpdated(){
        Category category = CategoryCreator.createCategoryWithId();
        return Product.builder()
                .id(1L)
                .name("Placa de vídeo")
                .category(category)
                .purchased(true)
                .spentPrice(new BigDecimal("1399.95"))
                .stipulatedPrice(new BigDecimal("1599.85"))
                .build();
    }

}
