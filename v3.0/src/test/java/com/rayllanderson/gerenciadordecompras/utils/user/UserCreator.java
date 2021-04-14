package com.rayllanderson.gerenciadordecompras.utils.user;

import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.model.User;

public class UserCreator {

    public static User createUserToBeSaved(){
        return User.builder().username("joao").password("123").email("whatever@email.com").name("João").build();
    }

    /**
     * @return User com ID 1
     */
    public static User createUserWithId(){
        return User.builder().id(1L).username("joao").password("123").email("whatever@email.com").name("João").build();
    }

    /**
     * @return UserPostRequestBody
     */
    public static UserPostRequestBody createUserPostRequestBody(){
        return UserPostRequestBody.builder().username("joao").password("123").email("whatever@email.com").name("João").build();
    }

    /**
     * @return UserPutRequestBody
     */
    public static UserPutRequestBody createUserPutRequestBody(){
        return UserPutRequestBody.builder().id(1L).username("joao").password("123").email("whatever@email.com").name("João").build();
    }
}
