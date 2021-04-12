package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.services.ProductService;
import com.rayllanderson.gerenciadordecompras.domain.services.StatisticService;
import com.rayllanderson.gerenciadordecompras.domain.validations.Assertions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/categories/{categoryId}/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final StatisticService statisticService;
    private final Assertions assertions;
/*
    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PathVariable Long categoryId, Pageable pageable) {
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        return ResponseEntity.ok(productService.findAll(currentCategory, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseBody> findById(@PathVariable Long categoryId, @PathVariable Long id){
        Long userId = 1L;
        assertions.assertThatCategoryIsValid(categoryId, userId);
        return ResponseEntity.ok(ProductMapper.toProductResponseBody(productService.findById(id, categoryId)));
    }

    @GetMapping("/purchased")
    public ResponseEntity<Page<Product>> findPurchased(@PathVariable Long categoryId, Pageable pageable) {
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        return ResponseEntity.ok(productService.findPurchased(currentCategory, pageable));
    }
    @GetMapping("/non-purchased")
    public ResponseEntity<Page<Product>> findNotPurchased(@PathVariable Long categoryId, Pageable pageable) {
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        return ResponseEntity.ok(productService.findNotPurchased(currentCategory, pageable));
    }

    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@PathVariable Long categoryId,
                                                        @RequestBody ProductPostRequestBody productPostRequestBody){
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productPostRequestBody, currentCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long categoryId, @PathVariable Long id,
                                       @RequestBody ProductPutRequestBody productPutRequestBody){
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        productPutRequestBody.setId(id);
        productService.update(productPutRequestBody, currentCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId, @PathVariable Long id){
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        productService.deleteById(id, currentCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@PathVariable Long categoryId, @RequestBody List<SelectItemsRequestBody> ids){
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        productService.deleteVariousById(ids, currentCategory);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@PathVariable Long categoryId, @RequestParam String name, Pageable pageable){
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        return ResponseEntity.ok(productService.findByName(name, currentCategory, pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> copyProductsToAnotherCategory(@PathVariable Long categoryId, @RequestBody TransferProductRequestBody data){
        Long userId = 1L;
        assertions.assertThatCategoryIsValid(data.getNewCategoryId(), userId);
        data.setCurrentCategoryId(categoryId);
        productService.copyProductsToAnotherCategory(data);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/move")
    public ResponseEntity<Void> moveProductsToAnotherCategory(@PathVariable Long categoryId,
                                                           @RequestBody TransferProductRequestBody data){
        Long userId = 1L;
        assertions.assertThatCategoryIsValid(data.getNewCategoryId(), userId);
        data.setCurrentCategoryId(categoryId);
        productService.moveProductsToAnotherCategory(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponseBody> getStatistics(@PathVariable Long categoryId){
        Long userId = 1L;
        return ResponseEntity.ok(statisticService.getStatistics(categoryId, userId));
    }*/
}
