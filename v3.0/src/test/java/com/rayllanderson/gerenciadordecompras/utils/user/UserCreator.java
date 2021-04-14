package com.rayllanderson.gerenciadordecompras.utils.user;

import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutPasswordRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.model.User;

public class UserCreator {

    public static User createUserToBeSaved(){
        return User.builder().username("joao").password("123").email("whatever@email.com").name("João").build();
    }

    public static User createAnotherUserToBeSaved(){
        return User.builder().username("kaguya").password("123").email("kaguya@sama.com").name("Kaguya").build();
    }

    /**
     * @return User com ID 1
     */
    public static User createUserWithId(){
        return User.builder().id(1L).username("joao").password("123").email("whatever@email.com").name("João").build();
    }

    public static UserPostRequestBody createUserToBeRegistered(){
        return UserPostRequestBody.builder().username("register").password("123").email("hahaha@email.com").name("Hey!").build();
    }

    public static UserPostRequestBody createUserPostRequestBody(){
        return UserPostRequestBody.builder().username("joao").password("123").email("whatever@email.com").name("João").build();
    }

    public static UserPostRequestBody createAnotherUserPostRequestBody(){
        return UserPostRequestBody.builder().username("kaguya").password("123").email("kaguya@sama.com").name("Kaguya").build();
    }

    /**
     * @return UserPutRequestBody
     */
    public static UserPutRequestBody createUserPutRequestBody(){
        return UserPutRequestBody.builder().id(1L).username("joao").email("whatever@email.com").name("João").build();
    }

    public static UserPutPasswordRequestBody createUserPutPasswordRequestBody(){
        return UserPutPasswordRequestBody.builder().id(1L).password("123").build();
    }

    public static UserResponseBody createUserResponseBody(){
        return UserResponseBody.builder().id(1L).username("joao").email("whatever@email.com").name("João").build();
    }
}
