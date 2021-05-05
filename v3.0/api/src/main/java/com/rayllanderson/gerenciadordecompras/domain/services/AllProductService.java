package com.rayllanderson.gerenciadordecompras.domain.services;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.AllProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferAllProductRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.validations.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllProductService {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Long userId, Pageable pageable){
        return productRepository.findAllByCategoryUserId(userId, pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllNonPageable(Long userId){
        return productRepository.findAllByCategoryUserId(userId);
    }

    @Transactional(readOnly = true)
    public Page<Product> findPurchased(Long userId, Pageable pageable){
        return productRepository.findPurchasedFromUser(userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> findNotPurchased(Long userId, Pageable pageable){
        return productRepository.findNonPurchasedFromUser(userId, pageable);
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId, Long userId){
        return productRepository.findByIdAndCategoryUserId(productId, userId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }

    public ProductPostResponseBody save (AllProductPostRequestBody product, Long userId){
        return productService.save(ProductMapper.toProductPostRequestBody(product), product.getCategoryId(), userId);
    }

    public void update (ProductPutRequestBody productPutRequestBody, Long userId){
        productService.update(productPutRequestBody, productPutRequestBody.getId(), userId);
    }

    public void deleteById (Long productId, Long userId){
        Category categoryFromProduct = getCategoryFromProduct(productId, userId);
        productService.deleteById(productId, categoryFromProduct.getId(), userId);
    }

    public void deleteVariousById(List<SelectItemsRequestBody> productsIds, Long userId){
        productsIds.forEach(req -> this.deleteById(req.getId(), userId));
    }

    @Transactional(readOnly = true)
    public Page<Product> findByName(String search, Long userId, Pageable pageable){
        boolean searchIsEmpty = search != null && (search.isEmpty() || search.trim().isEmpty());
        if (searchIsEmpty) {
            return Page.empty();
        }
        return productRepository.findByNameIgnoreCaseContainingAndCategoryUserId(search, userId, pageable);
    }

    @Transactional
    public void copyProductsToAnotherCategory(TransferAllProductRequestBody data, Long userId){
        List<Product> products = transformSelectItemsToProductList(data.getSelectItems(), userId);
        products.forEach(product -> {
            Product productToBeCopied = ProductMapper.createANewProduct(product);
            productToBeCopied.setId(null);
            productToBeCopied.setCategory(new Category(data.getNewCategoryId()));
            ProductValidation.validatePrices(product);
            productRepository.save(productToBeCopied);
        });
    }

    @Transactional
    public void moveProductsToAnotherCategory(TransferAllProductRequestBody data, Long userId){
        List<Product> products = transformSelectItemsToProductList(data.getSelectItems(), userId);
        products.forEach(product -> {
            Product productToBeMoved = ProductMapper.createANewProduct(product);
            productToBeMoved.setCategory(new Category(data.getNewCategoryId()));
            ProductValidation.validatePrices(product);
            productRepository.save(productToBeMoved);
        });
    }

    private List<Product> transformSelectItemsToProductList(List<SelectItemsRequestBody> items, Long userId){
        return items.stream()
                .map(req -> findById(req.getId(), userId))
                .collect(Collectors.toList());
    }

    private Category getCategoryFromProduct(Long productId, Long userId) {
        return this.findById(productId, userId).getCategory();
    }

}
