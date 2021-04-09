package com.rayllanderson.gerenciadordecompras.utils;

import com.rayllanderson.gerenciadordecompras.model.entities.User;

public class UserCreator {

    public static User createUserToBeSaved(){
        return User.builder().username("joao").password("123").email("whatever@email.com").name("João").build();
    }

}
