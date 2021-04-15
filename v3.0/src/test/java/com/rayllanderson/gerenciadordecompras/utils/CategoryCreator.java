package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.utils.user.UserCreator;

import java.math.BigDecimal;

public class CategoryCreator {

    public static Category createCategoryToBeSaved(){
        User user = UserCreator.createUserWithId();
        return Category.builder()
                .name("Pc")
                .budget(new BigDecimal(500))
                .user(user)
                .build();
    }

    /**
     * Faz a mesma coisa que @createCategoryToBeSaved, porém, com nome diferente
     * Usada para testar a troca de categoria de um produto.
     * @return uma categoria com um nome diferente.
     */
    public static Category createAnotherCategoryToBeSaved(){
        User user = UserCreator.createUserWithId();
        return Category.builder()
                .name("Compras")
                .budget(new BigDecimal(500))
                .user(user)
                .build();
    }

    public static Category createAnotherOneCategoryToBeSaved(){
        User user = UserCreator.createUserWithId();
        return Category.builder()
                .name("Festas")
                .budget(new BigDecimal(50))
                .user(user)
                .build();
    }

    /**
     * @return categoria com ID 1, nome 'PC' e Orçamento de 500
     */
    public static Category createCategoryWithId(){
        User user = UserCreator.createUserWithId();
        return Category.builder()
                .id(1L)
                .name("Pc")
                .budget(new BigDecimal(500))
                .user(user)
                .build();
    }

    public static Category createAnotherCategoryWithId(){
        User user = UserCreator.createUserWithId();
        return Category.builder()
                .id(2L)
                .name("Compras")
                .budget(new BigDecimal(50))
                .user(user)
                .build();
    }

    public static Category createCategoryToBeUpdated(){
        User user = UserCreator.createUserWithId();
        return Category.builder()
                .id(1L)
                .name("Compras")
                .budget(new BigDecimal(100))
                .user(user)
                .build();
    }

}
