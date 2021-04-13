package com.rayllanderson.gerenciadordecompras.domain.dtos.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class UserDtoModel {
    private String email;
    private String name;
    private String username;
    private String password;
}
