package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.core.requests.categories.TransferCategoryRequestBody;
import com.rayllanderson.gerenciadordecompras.core.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final Long userId = 1L;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable){
        return ResponseEntity.ok(categoryService.findAll(userId, pageable));
    }

    @PostMapping
    public ResponseEntity<CategoryPostResponseBody> save(@RequestBody CategoryPostRequestBody categoryPostRequestBody){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryPostRequestBody, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CategoryPutRequestBody categoryPutRequestBody){
        categoryPutRequestBody.setId(id);
        categoryService.update(categoryPutRequestBody, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.deleteById(id, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@RequestBody List<SelectItemsRequestBody> ids){
        categoryService.deleteVariousById(ids, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Category>> findByName(@RequestParam String name, Pageable pageable){
        return ResponseEntity.ok(categoryService.findByName(name, userId, pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> duplicateCategory(@RequestBody TransferCategoryRequestBody data){
        categoryService.duplicateCategory(data, userId);
        return ResponseEntity.noContent().build();
    }

}
