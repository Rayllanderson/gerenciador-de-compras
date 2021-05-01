package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.api.utils.UserUtil;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.category.CategoryPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.categories.TransferCategoryRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final UserUtil myUserUtil;

    @GetMapping
    public ResponseEntity<Page<Category>> findAllPageable(Pageable pageable, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(categoryService.findAll(userId, pageable));
    }

    @GetMapping("/non-pageable")
    public ResponseEntity<List<Category>> findAllNonPageable(@AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(categoryService.findAllNonPageable(userId));
    }

    @PostMapping
    public ResponseEntity<CategoryPostResponseBody> save(@RequestBody @Valid CategoryPostRequestBody categoryPostRequestBody,
                                                         @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryPostRequestBody, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CategoryPutRequestBody categoryPutRequestBody,
                                       @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        categoryPutRequestBody.setId(id);
        categoryService.update(categoryPutRequestBody, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(categoryService.findById(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        categoryService.deleteById(id, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@RequestBody @Valid List<SelectItemsRequestBody> ids, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        categoryService.deleteVariousById(ids, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Category>> findByName(@RequestParam String name, Pageable pageable, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(categoryService.findByName(name, userId, pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> duplicateCategory(@RequestBody @Valid TransferCategoryRequestBody data, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        categoryService.duplicateCategory(data, userId);
        return ResponseEntity.noContent().build();
    }
}
