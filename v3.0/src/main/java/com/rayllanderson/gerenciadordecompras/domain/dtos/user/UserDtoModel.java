package com.rayllanderson.gerenciadordecompras.domain.dtos.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class UserDtoModel {

    private String name;

    @Email
    @Size(max = 100)
    private String email;

    @Size(min = 3, max = 50)
    private String username;

    @Size(min = 3, max = 100)
    private String password;
}
