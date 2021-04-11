package com.rayllanderson.gerenciadordecompras.core.validations;

import com.rayllanderson.gerenciadordecompras.core.exceptions.BadRequestException;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Assertions {

    private final CategoryRepository categoryRepository;

    public Category assertThatCategoryIsValid(Long categoryId, Long userId){
        return categoryRepository.findByIdAndUserId(categoryId, userId).orElseThrow(() -> new BadRequestException("A categoria " +
                "selecionada não existe ou é inválida."));
    }
}
