package com.rayllanderson.gerenciadordecompras.domain.repositories;

import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);

    List<Product> findAllByCategoryId(Long categoryId);

    Page<Product> findByNameIgnoreCaseContainingAndCategoryId(String name, Long categoryId, Pageable pageable);

    List<Product> findByNameIgnoreCaseContainingAndCategoryId(String name, Long categoryId);

    List<Product> findByNameIgnoreCaseContainingAndCategoryUserId(String name, Long userId);

    Page<Product> findByNameIgnoreCaseContainingAndCategoryUserId(String name, Long userId, Pageable pageable);

    Optional<Product> findByIdAndCategoryId(Long id, Long categoryId);

    Page<Product> findAllByCategoryUserId(Long userId, Pageable pageable);

    List<Product> findAllByCategoryUserId(Long userId);

    Optional<Product> findByIdAndCategoryUserId(Long id, Long userId);

    @Query("from Product as p where p.purchased = true and p.category.id = ?1")
    Page<Product> findPurchasedFromCategory(Long categoryId, Pageable pageable);

    @Query("from Product as p where p.purchased = false and p.category.id = ?1")
    Page<Product> findNonPurchasedFromCategory(Long categoryId, Pageable pageable);

    @Query("from Product as p where p.purchased = true and p.category.user.id = ?1")
    Page<Product> findPurchasedFromUser(Long userId, Pageable pageable);

    @Query("from Product as p where p.purchased = false and p.category.user.id = ?1")
    Page<Product> findNonPurchasedFromUser(Long userId, Pageable pageable);
}
