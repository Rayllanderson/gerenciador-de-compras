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

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/categories/{categoryId}/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final StatisticService statisticService;
    private final Assertions assertions;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PathVariable Long categoryId, Pageable pageable) {
        Long userId = 1L;
        return ResponseEntity.ok(productService.findAll(categoryId, userId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseBody> findById(@PathVariable Long categoryId, @PathVariable Long id){
        Long userId = 1L;
        return ResponseEntity.ok(ProductMapper.toProductResponseBody(productService.findById(id, categoryId, userId)));
    }

    @GetMapping("/purchased")
    public ResponseEntity<Page<Product>> findPurchased(@PathVariable Long categoryId, Pageable pageable) {
        Long userId = 1L;
        return ResponseEntity.ok(productService.findPurchased(categoryId, userId, pageable));
    }
    @GetMapping("/non-purchased")
    public ResponseEntity<Page<Product>> findNotPurchased(@PathVariable Long categoryId, Pageable pageable) {
        Long userId = 1L;
        return ResponseEntity.ok(productService.findNotPurchased(categoryId, userId, pageable));
    }

    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@PathVariable Long categoryId,
                                                        @RequestBody @Valid ProductPostRequestBody productPostRequestBody){
        Long userId = 1L;
        Category currentCategory = assertions.assertThatCategoryIsValid(categoryId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productPostRequestBody, currentCategory.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long categoryId, @PathVariable Long id,
                                       @RequestBody @Valid ProductPutRequestBody productPutRequestBody){
        Long userId = 1L;
        productPutRequestBody.setId(id);
        productService.update(productPutRequestBody, categoryId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId, @PathVariable Long id){
        Long userId = 1L;
        productService.deleteById(id, categoryId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@PathVariable Long categoryId, @RequestBody @Valid List<SelectItemsRequestBody> ids){
        Long userId = 1L;
        productService.deleteVariousById(ids, categoryId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@PathVariable Long categoryId, @RequestParam String name, Pageable pageable){
        Long userId = 1L;
        return ResponseEntity.ok(productService.findByName(name, categoryId, userId, pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> copyProductsToAnotherCategory(@PathVariable Long categoryId,
                                                              @RequestBody @Valid TransferProductRequestBody data){
        Long userId = 1L;
        data.setCurrentCategoryId(categoryId);
        data.setUserId(userId);
        productService.copyProductsToAnotherCategory(data);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/move")
    public ResponseEntity<Void> moveProductsToAnotherCategory(@PathVariable Long categoryId,
                                                           @RequestBody @Valid TransferProductRequestBody data){
        Long userId = 1L;
        data.setCurrentCategoryId(categoryId);
        data.setUserId(userId);
        productService.moveProductsToAnotherCategory(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponseBody> getStatistics(@PathVariable Long categoryId){
        Long userId = 1L;
        return ResponseEntity.ok(statisticService.getStatistics(categoryId, userId));
    }
}
