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
    public Page<Product> findAll(Category category, Pageable pageable){
        return productRepository.findAllByCategoryId(category.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> findPurchased(Category category, Pageable pageable){
        return productRepository.findPurchasedFromCategory(category.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> findNotPurchased(Category category, Pageable pageable){
        return productRepository.findNonPurchasedFromCategory(category.getId(), pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllNonPageable(Category category){
        return productRepository.findAllByCategoryId(category.getId());
    }

    @Transactional
    public ProductPostResponseBody save(ProductPostRequestBody productPostRequestBody, Category category){
        Product product = ProductMapper.toProduct(productPostRequestBody);
        product.setCategory(category);
        return ProductMapper.toProductPostResponseBody(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId, Long categoryId){
        return productRepository.findByIdAndCategoryId(productId, categoryId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado."));
    }

    @Transactional
    public void update(ProductPutRequestBody productPutRequestBody, Category category){
        Product product = this.findById(productPutRequestBody.getId(), category.getId());
        UpdateData.updateProductData(productPutRequestBody, product);
        productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id, Category category){
        this.findById(id, category.getId());
        productRepository.deleteById(id);
    }

    public void deleteVariousById(List<SelectItemsRequestBody> productsIds, Category category){
        productsIds.forEach(req -> this.deleteById(req.getId(), category));
    }

    @Transactional(readOnly = true)
    public Page<Product> findByName(String search, Category category, Pageable pageable){
        boolean searchIsEmpty = search != null && (search.isEmpty() || search.trim().isEmpty());
        if (searchIsEmpty) {
            return Page.empty();
        }
        return productRepository.findByNameIgnoreCaseContainingAndCategoryId(search, category.getId(), pageable);
    }

    @Transactional
    public void copyProductsToAnotherCategory(TransferProductRequestBody data){
        List<Product> products = transformSelectItemsToProductList(data.getSelectItems(), data.getCurrentCategoryId());
        products.forEach(product -> {
            Product productToBeCopied = ProductMapper.createANewProduct(product);
            productToBeCopied.setId(null);
            productToBeCopied.setCategory(new Category(data.getNewCategoryId()));
            productRepository.save(productToBeCopied);
        });
    }

    @Transactional
    public void moveProductsToAnotherCategory(TransferProductRequestBody data){
        List<Product> products = transformSelectItemsToProductList(data.getSelectItems(), data.getCurrentCategoryId());
        products.forEach(product -> {
            Product productToBeMoved = ProductMapper.createANewProduct(product);
            productToBeMoved.setCategory(new Category(data.getNewCategoryId()));
            productRepository.save(productToBeMoved);
        });
    }

    private List<Product> transformSelectItemsToProductList(List<SelectItemsRequestBody> items, Long currentCategory){
        return items.stream()
                .map(req -> findById(req.getId(), currentCategory))
                .collect(Collectors.toList());
    }
}
