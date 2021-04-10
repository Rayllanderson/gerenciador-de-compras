package com.rayllanderson.gerenciadordecompras.core.services;

import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.core.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import com.rayllanderson.gerenciadordecompras.core.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.core.requests.SelectItemsRequestBody;
import com.rayllanderson.gerenciadordecompras.core.requests.products.TransferProductRequestBody;
import com.rayllanderson.gerenciadordecompras.core.services.utils.UpdateData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public Page<Product> findAll(Long categoryId, Pageable pageable){
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> findPurchased(Long categoryId, Pageable pageable){
        List<Product> purchasedProducts =
                productRepository.findAllByCategoryId(categoryId).stream().filter(Product::getPurchased).collect(Collectors.toList());
        return new PageImpl<>(purchasedProducts, pageable, purchasedProducts.size());
    }

    @Transactional(readOnly = true)
    public Page<Product> findNotPurchased(Long categoryId, Pageable pageable){
        List<Product> productsNotPurchased =
                productRepository.findAllByCategoryId(categoryId)
                        .stream()
                        .filter(product -> !product.getPurchased())
                        .collect(Collectors.toList());
        return new PageImpl<>(productsNotPurchased, pageable, productsNotPurchased.size());
    }

    @Transactional(readOnly = true)
    public List<Product> findAllNonPageable(Long categoryId){
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Transactional
    public ProductPostResponseBody save(ProductPostRequestBody productPostRequestBody, Long categoryId){
        Product product = ProductMapper.toProduct(productPostRequestBody);
        product.setCategory(new Category(categoryId));
        return ProductMapper.toProductPostResponseBody(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId, Long categoryId){
        return productRepository.findByIdAndCategoryId(productId, categoryId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    @Transactional
    public void update(ProductPutRequestBody productPutRequestBody, Long categoryId){
        Product product = findById(productPutRequestBody.getId(), categoryId);
        UpdateData.updateProductData(productPutRequestBody, product);
        productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id, Long categoryId){
        findById(id, categoryId);
        productRepository.deleteById(id);
    }

    public void deleteVariousById(List<SelectItemsRequestBody> productsIds, Long categoryId){
        productsIds.forEach(req -> this.deleteById(req.getId(), categoryId));
    }

    @Transactional(readOnly = true)
    public Page<Product> findByName(String search, Long categoryId, Pageable pageable){
        return productRepository.findByNameIgnoreCaseContainingAndCategoryId(search, categoryId, pageable);
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

    private List<Product> transformSelectItemsToProductList(List<SelectItemsRequestBody> items, Long currentCategoryId){
        return items.stream()
                .map(req -> findById(req.getId(), currentCategoryId))
                .collect(Collectors.toList());
    }
}
