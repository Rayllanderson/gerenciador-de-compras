package com.rayllanderson.gerenciadordecompras.domain.dtos.user;

import lombok.Data;

@Data
public class JwtLoginInput {
    private String username;
    private String password;
}