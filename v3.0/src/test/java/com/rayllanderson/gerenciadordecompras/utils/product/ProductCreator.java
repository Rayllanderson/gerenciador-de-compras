package com.rayllanderson.gerenciadordecompras.utils.product;

import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;

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

    /**
     * @return Produto com ID 2 e categoria diferente do @createProductWithId
     */
    public static Product createAnotherProductWithId(){
        Category category = CategoryCreator.createAnotherCategoryWithId();
        return Product.builder()
                .id(2L)
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
