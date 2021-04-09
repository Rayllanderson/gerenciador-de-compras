package com.rayllanderson.gerenciadordecompras.model.services;

import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.model.exceptions.NotFoundException;
import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Category;
import com.rayllanderson.gerenciadordecompras.model.entities.Product;
import com.rayllanderson.gerenciadordecompras.model.mapper.ProductMapper;
import com.rayllanderson.gerenciadordecompras.model.repositories.ProductRepository;
import com.rayllanderson.gerenciadordecompras.model.requests.DeleteVariousRequestBody;
import com.rayllanderson.gerenciadordecompras.model.services.utils.UpdateData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Long categoryId, Pageable pageable){
        return productRepository.findAllByCategoryId(categoryId, pageable);
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

    public void deleteVariousById(List<DeleteVariousRequestBody> productsIds, Long categoryId){
        productsIds.forEach(req -> this.deleteById(req.getId(), categoryId));
    }

    @Transactional(readOnly = true)
    public Page<Product> findByName(String search, Long categoryId, Pageable pageable){
        return productRepository.findByNameIgnoreCaseContainingAndCategoryId(search, categoryId, pageable);
    }
}
