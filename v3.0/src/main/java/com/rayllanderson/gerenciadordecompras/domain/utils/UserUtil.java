package com.rayllanderson.gerenciadordecompras.domain.utils;

import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public Long getUserIdByUsername(String username) throws NotFoundException{
        return userRepository.getIdFromUsername(username).orElseThrow(() -> new NotFoundException("user not found"));
    }
}
