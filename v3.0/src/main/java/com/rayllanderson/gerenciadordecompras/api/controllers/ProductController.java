package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Product;
import com.rayllanderson.gerenciadordecompras.model.requests.DeleteVariousRequestBody;
import com.rayllanderson.gerenciadordecompras.model.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final Long categoryId = 2L;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(categoryId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id, categoryId));
    }

    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@RequestBody ProductPostRequestBody productPostRequestBody){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(productPostRequestBody, categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductPutRequestBody productPutRequestBody){
        productPutRequestBody.setId(id);
        service.update(productPutRequestBody, categoryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteById(id, categoryId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@RequestBody List<DeleteVariousRequestBody> ids){
        service.deleteVariousById(ids, categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@RequestParam String name, Pageable pageable){
        return ResponseEntity.ok(service.findByName(name, categoryId, pageable));
    }
}
