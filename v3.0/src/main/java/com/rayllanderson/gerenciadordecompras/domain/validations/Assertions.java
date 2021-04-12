package com.rayllanderson.gerenciadordecompras.domain.validations;

import com.rayllanderson.gerenciadordecompras.domain.exceptions.BadRequestException;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.repositories.CategoryRepository;
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
