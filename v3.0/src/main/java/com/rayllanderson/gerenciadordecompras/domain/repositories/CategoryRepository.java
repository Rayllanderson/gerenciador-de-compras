package com.rayllanderson.gerenciadordecompras.domain.repositories;

import com.rayllanderson.gerenciadordecompras.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
