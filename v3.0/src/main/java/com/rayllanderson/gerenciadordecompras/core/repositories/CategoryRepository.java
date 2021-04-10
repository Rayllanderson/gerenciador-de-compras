package com.rayllanderson.gerenciadordecompras.core.repositories;

import com.rayllanderson.gerenciadordecompras.core.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByUserId(Long userId, Pageable pageable);
    List<Category> findAllByUserId(Long userId);
    Page<Category> findByNameIgnoreCaseContainingAndUserId(String name, Long userId, Pageable pageable);
    Optional<Category> findByIdAndUserId(Long id, Long userId);

}
