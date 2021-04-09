package com.rayllanderson.gerenciadordecompras.core.repositories;

import com.rayllanderson.gerenciadordecompras.core.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
    Page<Product> findByNameIgnoreCaseContainingAndCategoryId(String name, Long categoryId, Pageable pageable);
    Optional<Product> findByIdAndCategoryId(Long id, Long categoryId);
}
