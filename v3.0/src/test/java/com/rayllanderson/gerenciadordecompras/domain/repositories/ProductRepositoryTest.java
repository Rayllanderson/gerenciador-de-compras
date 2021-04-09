package com.rayllanderson.gerenciadordecompras.domain.repositories;

import com.rayllanderson.gerenciadordecompras.domain.entities.Category;
import com.rayllanderson.gerenciadordecompras.domain.entities.Product;
import com.rayllanderson.gerenciadordecompras.utils.CategoryCreator;
import com.rayllanderson.gerenciadordecompras.utils.ProductCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for ProductRepository")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void save_PersistProduct_WhenSuccessful() {
        Category category = CategoryCreator.createCategoryToBeSaved();
        category = categoryRepository.save(category);

        Product productToBeSaved = ProductCreator.createProductToBeSaved(category);
        Product productSaved = productRepository.save(productToBeSaved);

        Assertions.assertThat(productSaved).isNotNull();
        Assertions.assertThat(productSaved.getId()).isNotNull();
        Assertions.assertThat(productSaved.getName()).isEqualTo(productToBeSaved.getName());
        Assertions.assertThat(productSaved.getCategory()).isEqualTo(category);
    }

    @Test
    void save_UpdatesProduct_WhenSuccessful() {
        Category category = CategoryCreator.createCategoryToBeSaved();
        category = categoryRepository.save(category);

        Product productToBeSaved = ProductCreator.createProductToBeSaved(category);
        Product productSaved = productRepository.save(productToBeSaved);

        Assertions.assertThat(productSaved).isNotNull();

        Category newCategory = categoryRepository.save(CategoryCreator.createAnotherCategoryToBeSaved());
        productSaved.setName("Pasta Térmica");
        productSaved.setCategory(newCategory);

        Product productUpdated = productRepository.save(productSaved);

        Assertions.assertThat(productUpdated).isNotNull();
        Assertions.assertThat(productUpdated.getId()).isNotNull();
        Assertions.assertThat(productUpdated.getName()).isEqualTo(productSaved.getName());
        Assertions.assertThat(productUpdated.getCategory()).isNotEqualTo(category);
        Assertions.assertThat(productUpdated.getCategory()).isEqualTo(newCategory);
    }

    @Test
    void delete_RemovesProduct_WhenSuccessful() {
        Category category = CategoryCreator.createCategoryToBeSaved();
        category = categoryRepository.save(category);

        Product productToBeSaved = ProductCreator.createProductToBeSaved(category);
        Product productSaved = productRepository.save(productToBeSaved);

        productRepository.delete(productSaved);

        Optional<Product> productOptional = productRepository.findById(productSaved.getId());

        Assertions.assertThat(productOptional).isEmpty();
        Assertions.assertThat(productOptional).isNotPresent();
    }

}