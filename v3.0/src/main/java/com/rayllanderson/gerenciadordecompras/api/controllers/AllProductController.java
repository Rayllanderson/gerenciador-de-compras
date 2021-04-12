package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.AllProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/all-products")
public class AllProductController {

    private final StatisticService statisticService;
    private final AllProductService allProductService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        Long userId = 1L;
        return ResponseEntity.ok(allProductService.findAll(userId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseBody> findById(@PathVariable Long id){
        Long userId = 1L;
        return ResponseEntity.ok((ProductMapper.toProductResponseBody(allProductService.findById(id, userId)));
    }

    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@RequestBody AllProductPostRequestBody productPostRequestBody){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(allProductService.save(productPostRequestBody, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductPutRequestBody productPutRequestBody){
        Long userId = 1L;
        productPutRequestBody.setId(id);
        allProductService.update(productPutRequestBody, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Long userId = 1L;
        allProductService.deleteById(id, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@RequestBody List<SelectItemsRequestBody> ids){
        Long userId = 1L;
        allProductService.deleteVariousById(ids, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@RequestParam String name, Pageable pageable){
        Long userId = 1L;
        return ResponseEntity.ok(allProductService.findByName(name, userId, pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> copyProductsToAnotherCategory(@RequestBody TransferAllProductRequestBody data){
        Long userId = 1L;
        allProductService.copyProductsToAnotherCategory(data, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/move")
    public ResponseEntity<Void> moveProductsToAnotherCategory(@RequestBody TransferAllProductRequestBody data){
        Long userId = 1L;
        allProductService.moveProductsToAnotherCategory(data, userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponseBody> getStatistics() {
        Long userId = 1L;
        return ResponseEntity.ok(statisticService.getStatistics(userId));
    }
}
