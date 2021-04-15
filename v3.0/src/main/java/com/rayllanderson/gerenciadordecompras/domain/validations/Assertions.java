package com.rayllanderson.gerenciadordecompras.domain.validations;

import com.rayllanderson.gerenciadordecompras.domain.exceptions.BadRequestException;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.repositories.CategoryRepository;
import com.rayllanderson.gerenciadordecompras.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Assertions {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Category assertThatCategoryIsValid(Long categoryId, Long userId){
        String message = "A categoria selecionada não existe ou é inválida.";
        if (categoryId == null) throw new BadRequestException(message);
        return categoryRepository.findByIdAndUserId(categoryId, userId).orElseThrow(() -> new BadRequestException(message));
    }

    public void assertThatUsernameNotExists(String username) throws BadRequestException {
        boolean usernameExists = userRepository.existsByUsername(username);
        if (usernameExists) {
            throw new BadRequestException("Username já está em uso. Tente outro.");
        }
    }

    public void assertThatEmailNotExists(String email) throws BadRequestException{
        if (email == null) return;
        if (email.isEmpty() || email.trim().isEmpty()) return;
        boolean emailExists = userRepository.existsByEmail(email);
        if(emailExists){
            throw new BadRequestException("Email já cadastrado. Tente outro.");
        }
    }
}
