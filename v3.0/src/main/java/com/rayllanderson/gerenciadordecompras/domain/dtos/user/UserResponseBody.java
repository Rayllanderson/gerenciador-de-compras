package com.rayllanderson.gerenciadordecompras.domain.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseBody {
    private Long id;
    private String email;
    private String username;
    private String name;
}
