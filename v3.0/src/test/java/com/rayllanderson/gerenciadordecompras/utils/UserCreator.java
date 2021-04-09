package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.core.model.User;

public class UserCreator {

    public static User createUserToBeSaved(){
        return User.builder().username("joao").password("123").email("whatever@email.com").name("João").build();
    }

    /**
     *
     * @return User com ID 1
     */
    public static User createAValidUser(){
        return User.builder().id(1L).username("joao").password("123").email("whatever@email.com").name("João").build();
    }
}
