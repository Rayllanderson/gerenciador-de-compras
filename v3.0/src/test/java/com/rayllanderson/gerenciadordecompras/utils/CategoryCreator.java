package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import com.rayllanderson.gerenciadordecompras.model.entities.User;

import java.math.BigDecimal;

public class CategoryCreator {

    public static Category createCategoryToBeSaved(){
        User user = UserCreator.createAValidUser();
        return Category.builder()
                .name("Pc")
                .estimation(new BigDecimal(500))
                .user(user)
                .build();
    }

    /**
     * Faz a mesma coisa que @createCategoryToBeSaved, porém, com nome diferente
     * Usada para testar a troca de categoria de um produto.
     * @return uma categoria com um nome diferente.
     */
    public static Category createAnotherCategoryToBeSaved(){
        User user = UserCreator.createAValidUser();
        return Category.builder()
                .name("Compras")
                .estimation(new BigDecimal(500))
                .user(user)
                .build();
    }

    /**
     * @return categoria com ID 1, nome 'PC' e Orçamento de 500
     */
    public static Category createCategoryWithId(){
        User user = UserCreator.createAValidUser();
        return Category.builder()
                .id(1L)
                .name("Pc")
                .estimation(new BigDecimal(500))
                .user(user)
                .build();
    }

    public static Category createCategoryToBeUpdated(){
        User user = UserCreator.createAValidUser();
        return Category.builder()
                .id(1L)
                .name("Compras")
                .estimation(new BigDecimal(100))
                .user(user)
                .build();
    }

}
