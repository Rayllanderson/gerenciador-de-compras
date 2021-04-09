package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.model.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import com.rayllanderson.gerenciadordecompras.model.entities.User;

import java.math.BigDecimal;

public class CategoryPostRequestBodyCreator {

    public static CategoryPostRequestBody createCategoryPostRequestBody(){
        return CategoryPostRequestBody.builder()
                .name(CategoryCreator.createCategoryToBeSaved().getName())
                .estimation(CategoryCreator.createCategoryToBeSaved().getEstimation())
                .build();
    }

    /**
     * Faz a mesma coisa que @createCategoryPostRequestBody, porém, com nome diferente
     * Usada para testar a troca de categoria de um produto.
     * @return um CategoryPostRequestBody com um nome diferente.
     */
    public static CategoryPostRequestBody createAnotherCategoryPostRequestBody(){
        return CategoryPostRequestBody.builder()
                .name("Compras")
                .estimation(CategoryCreator.createCategoryToBeSaved().getEstimation())
                .build();
    }

}
