package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.User;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.categories.TransferCategoryRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.services.CategoryService;
import com.rayllanderson.gerenciadordecompras.domain.utils.CategoryUtil;
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
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryUtil categoryUtil;

    @GetMapping
    public ResponseEntity<Page<Category>> findAllPageable(Pageable pageable, @AuthenticationPrincipal User userAuthenticated){
        Page<Category> categories = categoryService.findAll(userAuthenticated.getId(), pageable);
        categoryUtil.setCompletedPercentage(categories.toList());
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/non-pageable")
    public ResponseEntity<List<Category>> findAllNonPageable(@AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.ok(categoryService.findAllNonPageable(userAuthenticated.getId()));
    }

    @PostMapping
    public ResponseEntity<CategoryPostResponseBody> save(@RequestBody @Valid CategoryPostRequestBody categoryPostRequestBody,
                                                         @AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryPostRequestBody, userAuthenticated.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CategoryPutRequestBody categoryPutRequestBody,
                                       @AuthenticationPrincipal User userAuthenticated){
        categoryPutRequestBody.setId(id);
        categoryService.update(categoryPutRequestBody, userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id, @AuthenticationPrincipal User userAuthenticated){
        return ResponseEntity.ok(categoryService.findById(id, userAuthenticated.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal User userAuthenticated){
        categoryService.deleteById(id, userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@RequestBody @Valid List<SelectItemsRequestBody> ids, @AuthenticationPrincipal User userAuthenticated){
        categoryService.deleteVariousById(ids, userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Category>> findByName(@RequestParam String name, Pageable pageable, @AuthenticationPrincipal User userAuthenticated){
        Page <Category> categories = categoryService.findByName(name, userAuthenticated.getId(), pageable);
        categoryUtil.setCompletedPercentage(categories.toList());
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> duplicateCategory(@RequestBody @Valid TransferCategoryRequestBody data, @AuthenticationPrincipal User userAuthenticated){
        categoryService.duplicateCategory(data, userAuthenticated.getId());
        return ResponseEntity.noContent().build();
    }
}
