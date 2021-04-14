package com.rayllanderson.gerenciadordecompras.api.utils;

import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("myUserUtil")
public class UserUtil {

    private final UserRepository userRepository;

    public Long getUserId(UserDetails user) {
        return userRepository.getIdFromUsername(user.getUsername()).orElseThrow(() -> new NotFoundException("user not found"));
    }
}
