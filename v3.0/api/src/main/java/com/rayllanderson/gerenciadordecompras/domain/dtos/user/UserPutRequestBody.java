package com.rayllanderson.gerenciadordecompras.domain.dtos.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserPutRequestBody {

    @NotNull
    private Long id;

    private String name;

    @Email
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;
}
