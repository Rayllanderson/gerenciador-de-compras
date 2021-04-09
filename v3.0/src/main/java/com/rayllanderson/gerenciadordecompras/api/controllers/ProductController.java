package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import com.rayllanderson.gerenciadordecompras.core.requests.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.core.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.core.services.ProductService;
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

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PathVariable Long categoryId, Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(categoryId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long categoryId, @PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id, categoryId));
    }

    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@PathVariable Long categoryId,
                                                        @RequestBody ProductPostRequestBody productPostRequestBody){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productPostRequestBody, categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long categoryId, @PathVariable Long id,
                                       @RequestBody ProductPutRequestBody productPutRequestBody){
        productPutRequestBody.setId(id);
        productService.update(productPutRequestBody, categoryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long categoryId, @PathVariable Long id){
        productService.deleteById(id, categoryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@PathVariable Long categoryId, @RequestBody List<SelectItemsRequestBody> ids){
        productService.deleteVariousById(ids, categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@PathVariable Long categoryId, @RequestParam String name, Pageable pageable){
        return ResponseEntity.ok(productService.findByName(name, categoryId, pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> copyProducts(@PathVariable Long categoryId, @RequestBody TransferProductRequestBody data){
        data.setCurrentCategoryId(categoryId);
        productService.copyProductsToAnotherCategory(data);
        return ResponseEntity.noContent().build();
    }
}
