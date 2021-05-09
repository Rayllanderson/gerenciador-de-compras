package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.services.ProductService;
import com.rayllanderson.gerenciadordecompras.domain.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/categories/{categoryId}/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PathVariable Long categoryId, Pageable pageable,
                                                 @AuthenticationPrincipal User userAuthenticated) {
        return ResponseEntity.ok(productService.findAll(categoryId, userAuthenticated.getId(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseBody> findById(@PathVariable Long categoryId, @PathVariable Long id,
                                                        @AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.ok(ProductMapper.toProductResponseBody(productService.findById(id, categoryId, userAuthenticated.getId())));
    }

    @GetMapping("/purchased")
    public ResponseEntity<Page<Product>> findPurchased(@PathVariable Long categoryId, Pageable pageable,
                                                       @AuthenticationPrincipal User userAuthenticated) {
        return ResponseEntity.ok(productService.findPurchased(categoryId, userAuthenticated.getId(), pageable));
    }
    @GetMapping("/non-purchased")
    public ResponseEntity<Page<Product>> findNotPurchased(@PathVariable Long categoryId, Pageable pageable,
                                                          @AuthenticationPrincipal User userAuthenticated) {
        return ResponseEntity.ok(productService.findNotPurchased(categoryId, userAuthenticated.getId(), pageable));
    }

    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@PathVariable Long categoryId,
                                                        @RequestBody @Valid ProductPostRequestBody productPostRequestBody,
                                                        @AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productPostRequestBody, categoryId, userAuthenticated.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long categoryId, @PathVariable Long id,
                                       @RequestBody @Valid ProductPutRequestBody productPutRequestBody,
                                       @AuthenticationPrincipal User userAuthenticated){
        productPutRequestBody.setId(id);
        productService.update(productPutRequestBody, categoryId, userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId, @PathVariable Long id,
                                       @AuthenticationPrincipal User userAuthenticated){
        productService.deleteById(id, categoryId, userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@PathVariable Long categoryId, @RequestBody @Valid List<SelectItemsRequestBody> ids,
                                              @AuthenticationPrincipal User userAuthenticated){
        productService.deleteVariousById(ids, categoryId, userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@PathVariable Long categoryId, @RequestParam String name, Pageable pageable,
                                                    @AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.ok(productService.findByName(name, categoryId, userAuthenticated.getId(), pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> copyProductsToAnotherCategory(@PathVariable Long categoryId,
                                                              @RequestBody @Valid TransferProductRequestBody data,
                                                              @AuthenticationPrincipal User userAuthenticated){
        data.setCurrentCategoryId(categoryId);
        data.setUserId(userAuthenticated.getId());
        productService.copyProductsToAnotherCategory(data);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/move")
    public ResponseEntity<Void> moveProductsToAnotherCategory(@PathVariable Long categoryId,
                                                              @RequestBody @Valid TransferProductRequestBody data,
                                                              @AuthenticationPrincipal User userAuthenticated){
        data.setCurrentCategoryId(categoryId);
        data.setUserId(userAuthenticated.getId());
        productService.moveProductsToAnotherCategory(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponseBody> getStatistics(@PathVariable Long categoryId,
                                                               @AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.ok(statisticService.getStatistics(categoryId, userAuthenticated.getId()));
    }
}
