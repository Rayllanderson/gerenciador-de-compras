package com.rayllanderson.gerenciadordecompras.model.mapper;

import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPostRequestBody;
import com.rayllanderson.gerenciadordecompras.model.dtos.product.ProductPutRequestBody;
import com.rayllanderson.gerenciadordecompras.model.entities.Product;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product toProduct(ProductPostRequestBody productPostRequestBody){
        return new ModelMapper().map(productPostRequestBody, Product.class);
    }

    public static Product toProduct(ProductPutRequestBody categoryPutRequestBody){
        return new ModelMapper().map(categoryPutRequestBody, Product.class);
    }

    public static ProductPostRequestBody toProductPostRequestBody(Product category){
        return new ModelMapper().map(category, ProductPostRequestBody.class);
    }

    public static ProductPutRequestBody toProductPutRequestBody(Product category){
        return new ModelMapper().map(category, ProductPutRequestBody.class);
    }
}
