package com.rayllanderson.gerenciadordecompras.domain.repositories;


import com.rayllanderson.gerenciadordecompras.domain.entities.Category;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for CategoryRepository")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Test
    void save_PersistCategory_WhenSuccessful() {
        Category categoryToBeSaved = CategoryCreator.createCategoryToBeSaved();
        Category categorySaved = categoryRepository.save(categoryToBeSaved);

        Assertions.assertThat(categorySaved).isNotNull();
        Assertions.assertThat(categorySaved.getId()).isNotNull();
        Assertions.assertThat(categorySaved.getName()).isEqualTo(categoryToBeSaved.getName());
        Assertions.assertThat(categorySaved.getEstimation()).isEqualTo(categoryToBeSaved.getEstimation());
    }

    @Test
    void save_UpdatesCategory_WhenSuccessful() {
        Category categoryToBeSaved = CategoryCreator.createCategoryToBeSaved();
        Category categorySaved = categoryRepository.save(categoryToBeSaved);

        Assertions.assertThat(categorySaved).isNotNull();

        categorySaved.setName("Compras");
        categorySaved.setEstimation(new BigDecimal(50));

        Category categoryUpdated = categoryRepository.save(categorySaved);

        Assertions.assertThat(categoryUpdated).isNotNull();
        Assertions.assertThat(categoryUpdated.getId()).isNotNull();
        Assertions.assertThat(categoryUpdated.getName()).isEqualTo(categorySaved.getName());
    }

    @Test
    void delete_RemovesCategory_WhenSuccessful() {
        Category categoryToBeSaved = CategoryCreator.createCategoryToBeSaved();
        Category categorySaved = categoryRepository.save(categoryToBeSaved);

        categoryRepository.delete(categorySaved);

        Optional<Category> categoryOptional = categoryRepository.findById(categorySaved.getId());

        Assertions.assertThat(categoryOptional).isEmpty();
        Assertions.assertThat(categoryOptional).isNotPresent();
    }

}