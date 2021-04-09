package com.rayllanderson.gerenciadordecompras.model.repositories;

import com.rayllanderson.gerenciadordecompras.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
