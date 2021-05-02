package com.rayllanderson.gerenciadordecompras.domain.repositories;

import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryIdAndCategoryUserId(Long categoryId, Long userId, Pageable pageable);

    List<Product> findAllByCategoryIdAndCategoryUserId(Long categoryId, Long userId);

    Page<Product> findByNameIgnoreCaseContainingAndCategoryIdAndCategoryUserId(String name, Long categoryId,  Long userId, Pageable pageable);

    List<Product> findByNameIgnoreCaseContainingAndCategoryIdAndCategoryUserId(String name, Long categoryId,  Long userId);

    List<Product> findByNameIgnoreCaseContainingAndCategoryUserId(String name, Long userId);

    Page<Product> findByNameIgnoreCaseContainingAndCategoryUserId(String name, Long userId, Pageable pageable);

    Optional<Product> findByIdAndCategoryIdAndCategoryUserId(Long id, Long categoryId, Long userId);

    Page<Product> findAllByCategoryUserId(Long userId, Pageable pageable);

    List<Product> findAllByCategoryUserId(Long userId);

    Optional<Product> findByIdAndCategoryUserId(Long id, Long userId);

    @Query("from Product as p where p.purchased = true and p.category.id = ?1 and p.category.user.id = ?2")
    Page<Product> findPurchasedFromCategory(Long categoryId, Long userId, Pageable pageable);

    @Query("from Product as p where p.purchased = false and p.category.id = ?1 and p.category.user.id = ?2")
    Page<Product> findNonPurchasedFromCategory(Long categoryId, Long userId, Pageable pageable);

    @Query("from Product as p where p.purchased = true and p.category.user.id = ?1")
    Page<Product> findPurchasedFromUser(Long userId, Pageable pageable);

    @Query("from Product as p where p.purchased = false and p.category.user.id = ?1")
    Page<Product> findNonPurchasedFromUser(Long userId, Pageable pageable);

    Integer countProductByCategoryIdAndCategoryUserId(Long categoryId, Long userId);

    @Query("select count (p) from Product as p where p.purchased = true and p.category.id = ?1 and p.category.user.id = ?2")
    Integer countProductPurchased(Long categoryId, Long userId);

    @Query("select count (p) from Product as p where p.purchased = false and p.category.id = ?1 and p.category.user.id = ?2")
    Integer countProductNonPurchased(Long categoryId, Long userId);

}
