package com.rayllanderson.gerenciadordecompras.core.mapper;

import com.rayllanderson.gerenciadordecompras.core.dtos.product.AllProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.core.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.core.model.Product;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product toProduct(ProductPostRequestBody productPostRequestBody){
        return new ModelMapper().map(productPostRequestBody, Product.class);
    }

    public static Product toProduct(ProductPutRequestBody productPutRequestBody){
        return new ModelMapper().map(productPutRequestBody, Product.class);
    }

    /**
     * Gerará um novo objeto - não manipulado pelo Hibernate -  com os mesmos dados do objeto passado no parâmetro.
     * @param product um objeto que vem do banco, por exemplo.
     * @return um NOVO objeto com os mesmos dados.
     */
    public static Product createANewProduct(Product product){
        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setStipulatedPrice(product.getStipulatedPrice());
        newProduct.setSpentPrice(product.getSpentPrice());
        newProduct.setPurchased(product.getPurchased());
        newProduct.setCategory(product.getCategory());
        return newProduct;
    }

    public static ProductPostRequestBody toProductPostRequestBody(Product product){
        return new ModelMapper().map(product, ProductPostRequestBody.class);
    }

    public static ProductPutRequestBody toProductPutRequestBody(Product product){
        return new ModelMapper().map(product, ProductPutRequestBody.class);
    }

    public static ProductPostResponseBody toProductPostResponseBody(Product product){
        return new ModelMapper().map(product, ProductPostResponseBody.class);
    }

    public static ProductPostRequestBody toProductPostRequestBody(AllProductPostRequestBody product){
        return new ModelMapper().map(product, ProductPostRequestBody.class);
    }
}
