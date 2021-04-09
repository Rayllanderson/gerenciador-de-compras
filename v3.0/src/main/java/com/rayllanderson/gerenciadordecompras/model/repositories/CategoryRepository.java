package com.rayllanderson.gerenciadordecompras.model.repositories;

import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByUserId(Long userId, Pageable pageable);
    Page<Category> findByNameIgnoreCaseContainingAndUserId(String name, Long userId, Pageable pageable);
    Optional<Category> findByIdAndUserId(Long id, Long userId);

}
