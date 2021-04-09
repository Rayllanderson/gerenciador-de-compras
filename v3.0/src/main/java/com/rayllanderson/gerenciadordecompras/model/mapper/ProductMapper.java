package com.rayllanderson.gerenciadordecompras.model.mapper;

import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostResponseBody;
import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Product;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product toProduct(ProductPostRequestBody productPostRequestBody){
        return new ModelMapper().map(productPostRequestBody, Product.class);
    }

    public static Product toProduct(ProductPutRequestBody productPutRequestBody){
        return new ModelMapper().map(productPutRequestBody, Product.class);
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
}
