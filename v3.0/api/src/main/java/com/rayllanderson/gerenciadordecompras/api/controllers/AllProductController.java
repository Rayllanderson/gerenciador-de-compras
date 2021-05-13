package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.AllProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.StatisticResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferAllProductRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.services.AllProductService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/all-products")
public class AllProductController {

    private final StatisticService statisticService;
    private final AllProductService allProductService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable, @AuthenticationPrincipal User authenticatedUser) {
        return ResponseEntity.ok(allProductService.findAll(authenticatedUser.getId(), pageable));
    }

    @GetMapping("/purchased")
    public ResponseEntity<Page<Product>> findAllPurchased(Pageable pageable, @AuthenticationPrincipal User authenticatedUser) {
        return ResponseEntity.ok(allProductService.findPurchased(authenticatedUser.getId(), pageable));
    }

    @GetMapping("/non-purchased")
    public ResponseEntity<Page<Product>> findAllNonPurchased(Pageable pageable, @AuthenticationPrincipal User authenticatedUser) {
        return ResponseEntity.ok(allProductService.findNotPurchased(authenticatedUser.getId(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseBody> findById(@PathVariable Long id, @AuthenticationPrincipal User authenticatedUser) {
        Product foundProduct = allProductService.findById(id, authenticatedUser.getId());
        return ResponseEntity.ok(ProductMapper.toProductResponseBody(foundProduct));
    }

    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@RequestBody @Valid AllProductPostRequestBody productPostRequestBody,
                                                        @AuthenticationPrincipal User authenticatedUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(allProductService.save(productPostRequestBody,
                authenticatedUser.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductPutRequestBody productPutRequestBody,
                                       @AuthenticationPrincipal User authenticatedUser) {
        productPutRequestBody.setId(id);
        allProductService.update(productPutRequestBody, authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User authenticatedUser) {
        allProductService.deleteById(id, authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@RequestBody @Valid List<SelectItemsRequestBody> ids,
                                              @AuthenticationPrincipal User authenticatedUser) {
        allProductService.deleteVariousById(ids, authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@RequestParam String name, Pageable pageable,
                                                    @AuthenticationPrincipal User authenticatedUser) {
        return ResponseEntity.ok(allProductService.findByName(name, authenticatedUser.getId(), pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> copyProductsToAnotherCategory(@RequestBody @Valid TransferAllProductRequestBody data,
                                                              @AuthenticationPrincipal User authenticatedUser) {
        allProductService.copyProductsToAnotherCategory(data, authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/move")
    public ResponseEntity<Void> moveProductsToAnotherCategory(@RequestBody @Valid TransferAllProductRequestBody data,
                                                              @AuthenticationPrincipal User authenticatedUser) {
        allProductService.moveProductsToAnotherCategory(data, authenticatedUser.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponseBody> getStatistics(@AuthenticationPrincipal User authenticatedUser) {
        return ResponseEntity.ok(statisticService.getStatistics(authenticatedUser.getId()));
    }
}
