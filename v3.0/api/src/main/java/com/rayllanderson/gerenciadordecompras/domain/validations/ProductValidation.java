package com.rayllanderson.gerenciadordecompras.domain.validations;

import com.rayllanderson.gerenciadordecompras.domain.model.Product;

import java.math.BigDecimal;

public class ProductValidation {

    public static void validatePrices(Product product){
        if (product.getSpentPrice() == null) product.setSpentPrice(BigDecimal.ZERO);
        if (product.getStipulatedPrice() == null) product.setStipulatedPrice(BigDecimal.ZERO);
    }
}
