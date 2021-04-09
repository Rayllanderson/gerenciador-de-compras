package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.domain.entities.Category;

import java.math.BigDecimal;

public class CategoryCreator {

    public static Category createCategoryToBeSaved(){
        return Category.builder()
                .name("Pc")
                .estimation(new BigDecimal(500))
                .build();
    }

    /**
     * Faz a mesma coisa que @createCategoryToBeSaved, porém, com nome diferente
     * Usada para testar a troca de categoria de um produto.
     * @return uma categoria com um nome diferente.
     */
    public static Category createAnotherCategoryToBeSaved(){
        return Category.builder()
                .name("Compras")
                .estimation(new BigDecimal(500))
                .build();
    }

    public static Category createCategoryWithId(){
        return Category.builder()
                .id(1L)
                .name("Pc")
                .estimation(new BigDecimal(500))
                .build();
    }

    public static Category createCategoryToBeUpdated(){
        return Category.builder()
                .id(1L)
                .name("Compras")
                .estimation(new BigDecimal(100))
                .build();
    }

}
