package com.rayllanderson.gerenciadordecompras.domain.repositories;

import com.rayllanderson.gerenciadordecompras.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
