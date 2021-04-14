package com.rayllanderson.gerenciadordecompras.domain.dtos.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class UserPutRequestBody extends UserDtoModel{
    private Long id;
}
