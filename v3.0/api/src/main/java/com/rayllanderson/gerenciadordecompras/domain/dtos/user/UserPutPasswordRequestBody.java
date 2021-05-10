package com.rayllanderson.gerenciadordecompras.domain.dtos.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserPutPasswordRequestBody  {

    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}
