package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPostRequestBody;

public class CategoryPostRequestBodyCreator {

    public static CategoryPostRequestBody createCategoryPostRequestBody(){
        return CategoryPostRequestBody.builder()
                .name(CategoryCreator.createCategoryToBeSaved().getName())
                .budget(CategoryCreator.createCategoryToBeSaved().getBudget())
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
                .budget(CategoryCreator.createCategoryToBeSaved().getBudget())
                .build();
    }

}
