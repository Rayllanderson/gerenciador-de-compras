package com.rayllanderson.gerenciadordecompras.core.services;

import com.rayllanderson.gerenciadordecompras.core.dtos.product.AllProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.core.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import com.rayllanderson.gerenciadordecompras.core.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.core.validations.Assertions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllProductService {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Assertions assertions;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Long userId, Pageable pageable){
        List<Category> allCategories = categoryService.findAllNonPageable(userId);
        List<Product> allProducts = allCategories
                .stream()
                .flatMap(category -> productService.findAllNonPageable(category).stream())
                .collect(Collectors.toList());
        return new PageImpl<>(allProducts, pageable, allProducts.size());
    }

    @Transactional(readOnly = true)
    public List<Product> findAllNonPageable(Long userId){
        List<Category> allCategories = categoryService.findAllNonPageable(userId);
        return allCategories
                .stream()
                .flatMap(category -> productService.findAllNonPageable(category).stream())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Product> findPurchased(Long userId, Pageable pageable){
        List<Category> allCategories = categoryService.findAllNonPageable(userId);
        List<Product> purchasedProducts = allCategories
                .stream()
                .flatMap(category -> productService.findPurchased(category, pageable).stream())
                .collect(Collectors.toList());
        return new PageImpl<>(purchasedProducts, pageable, purchasedProducts.size());
    }

    @Transactional(readOnly = true)
    public Page<Product> findNotPurchased(Long userId, Pageable pageable){
        List<Category> allCategories = categoryService.findAllNonPageable(userId);
        List<Product> productsNotPurchased = allCategories
                .stream()
                .flatMap(category -> productService.findNotPurchased(category, pageable).stream())
                .collect(Collectors.toList());
        return new PageImpl<>(productsNotPurchased, pageable, productsNotPurchased.size());
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId, Long userId){
        List<Product> allProducts = this.findAllNonPageable(userId);
        return allProducts.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }

    public ProductPostResponseBody save (AllProductPostRequestBody product, Long userId){
        Category category = assertions.assertThatCategoryIsValid(product.getCategoryId(), userId);
        return productService.save(ProductMapper.toProductPostRequestBody(product), category);
    }


}
