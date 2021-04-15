package com.rayllanderson.gerenciadordecompras.domain.mapper;

import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.user.UserPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static User toUser(UserPostRequestBody userPostRequestBody){
        return new ModelMapper().map(userPostRequestBody, User.class);
    }

    public static User toUser(UserPutRequestBody userPutRequestBody){
        return new ModelMapper().map(userPutRequestBody, User.class);
    }

    public static UserPostRequestBody toUserPostRequestBody(User user){
        return new ModelMapper().map(user, UserPostRequestBody.class);
    }

    public static UserPutRequestBody toUserPutRequestBody(User user){
        return new ModelMapper().map(user, UserPutRequestBody.class);
    }

    public static UserResponseBody toUserResponseBody(User user){
        return new ModelMapper().map(user, UserResponseBody.class);
    }
}
