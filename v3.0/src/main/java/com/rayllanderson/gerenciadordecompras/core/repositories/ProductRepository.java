package com.rayllanderson.gerenciadordecompras.core.repositories;

import com.rayllanderson.gerenciadordecompras.core.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
    List<Product> findAllByCategoryId(Long categoryId);
    Page<Product> findByNameIgnoreCaseContainingAndCategoryId(String name, Long categoryId, Pageable pageable);
    List<Product> findByNameIgnoreCaseContainingAndCategoryId(String name, Long categoryId);
    Optional<Product> findByIdAndCategoryId(Long id, Long categoryId);
}
