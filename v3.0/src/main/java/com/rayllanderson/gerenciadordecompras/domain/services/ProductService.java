package com.rayllanderson.gerenciadordecompras.domain.services;

import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.domain.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.domain.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.domain.model.Category;
import com.rayllanderson.gerenciadordecompras.domain.model.Product;
import com.rayllanderson.gerenciadordecompras.domain.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.domain.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.domain.services.utils.UpdateData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Long categoryId, Long userId, Pageable pageable){
        return productRepository.findAllByCategoryIdAndCategoryUserId(categoryId, userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> findPurchased(Long categoryId, Long userId, Pageable pageable){
        return productRepository.findPurchasedFromCategory(categoryId, userId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> findNotPurchased(Long categoryId, Long userId, Pageable pageable){
        return productRepository.findNonPurchasedFromCategory(categoryId, userId, pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllNonPageable(Long categoryId, Long userId){
        return productRepository.findAllByCategoryIdAndCategoryUserId(categoryId, userId);
    }

    @Transactional
    public ProductPostResponseBody save(ProductPostRequestBody productPostRequestBody, Long categoryId){
        Product product = ProductMapper.toProduct(productPostRequestBody);
        product.setCategory(new Category(categoryId));
        return ProductMapper.toProductPostResponseBody(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId, Long categoryId, Long userId){
        return productRepository.findByIdAndCategoryIdAndCategoryUserId(productId, categoryId, userId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }

    @Transactional
    public void update(ProductPutRequestBody productPutRequestBody, Long categoryId, Long userId){
        Product product = this.findById(productPutRequestBody.getId(), categoryId, userId);
        UpdateData.updateProductData(productPutRequestBody, product);
        productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id, Long categoryId, Long userId){
        this.findById(id, categoryId, userId);
        productRepository.deleteById(id);
    }

    public void deleteVariousById(List<SelectItemsRequestBody> productsIds, Long categoryId, Long userId){
        productsIds.forEach(req -> this.deleteById(req.getId(), categoryId, userId));
    }

    @Transactional(readOnly = true)
    public Page<Product> findByName(String search, Long categoryId, Long userId, Pageable pageable){
        boolean searchIsEmpty = search != null && (search.isEmpty() || search.trim().isEmpty());
        if (searchIsEmpty) {
            return Page.empty();
        }
        return productRepository.findByNameIgnoreCaseContainingAndCategoryIdAndCategoryUserId(search, categoryId, userId, pageable);
    }

    @Transactional
    public void copyProductsToAnotherCategory(TransferProductRequestBody data){
        List<Product> products = transformSelectItemsToProductList(data.getSelectItems(), data.getCurrentCategoryId(), data.getUserId());
        products.forEach(product -> {
            Product productToBeCopied = ProductMapper.createANewProduct(product);
            productToBeCopied.setId(null);
            productToBeCopied.setCategory(new Category(data.getNewCategoryId()));
            productRepository.save(productToBeCopied);
        });
    }

    @Transactional
    public void moveProductsToAnotherCategory(TransferProductRequestBody data){
        List<Product> products = transformSelectItemsToProductList(data.getSelectItems(), data.getCurrentCategoryId(), data.getUserId());
        products.forEach(product -> {
            Product productToBeMoved = ProductMapper.createANewProduct(product);
            productToBeMoved.setCategory(new Category(data.getNewCategoryId()));
            productRepository.save(productToBeMoved);
        });
    }

    private List<Product> transformSelectItemsToProductList(List<SelectItemsRequestBody> items, Long currentCategory, Long userId){
        return items.stream()
                .map(req -> findById(req.getId(), currentCategory, userId))
                .collect(Collectors.toList());
    }
}
