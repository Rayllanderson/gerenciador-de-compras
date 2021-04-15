package com.rayllanderson.gerenciadordecompras.api.controllers;

import com.rayllanderson.gerenciadordecompras.api.utils.UserUtil;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/all-products")
public class AllProductController {

    private final StatisticService statisticService;
    private final AllProductService allProductService;
    private final UserUtil myUserUtil;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable, @AuthenticationPrincipal UserDetails user) {
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(allProductService.findAll(userId, pageable));
    }

    @GetMapping("/purchased")
    public ResponseEntity<Page<Product>> findAllPurchased(Pageable pageable, @AuthenticationPrincipal UserDetails user) {
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(allProductService.findPurchased(userId, pageable));
    }

    @GetMapping("/non-purchased")
    public ResponseEntity<Page<Product>> findAllNonPurchased(Pageable pageable, @AuthenticationPrincipal UserDetails user) {
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(allProductService.findNotPurchased(userId, pageable));
    }


    @PostMapping
    public ResponseEntity<ProductPostResponseBody> save(@RequestBody @Valid AllProductPostRequestBody productPostRequestBody,
                                                        @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(allProductService.save(productPostRequestBody, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        allProductService.deleteById(id, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVarious(@RequestBody @Valid List<SelectItemsRequestBody> ids,
                                              @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        allProductService.deleteVariousById(ids, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> findByName(@RequestParam String name, Pageable pageable,
                                                    @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(allProductService.findByName(name, userId, pageable));
    }

    @PostMapping("/copy")
    public ResponseEntity<Void> copyProductsToAnotherCategory(@RequestBody @Valid TransferAllProductRequestBody data,
                                                              @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        allProductService.copyProductsToAnotherCategory(data, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/move")
    public ResponseEntity<Void> moveProductsToAnotherCategory(@RequestBody @Valid TransferAllProductRequestBody data,
                                                              @AuthenticationPrincipal UserDetails user){
        Long userId = myUserUtil.getUserId(user);
        allProductService.moveProductsToAnotherCategory(data, userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/statistics")
    public ResponseEntity<StatisticResponseBody> getStatistics(@AuthenticationPrincipal UserDetails user) {
        Long userId = myUserUtil.getUserId(user);
        return ResponseEntity.ok(statisticService.getStatistics(userId));
    }
}
