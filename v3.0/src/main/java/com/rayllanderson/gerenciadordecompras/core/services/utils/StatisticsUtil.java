package com.rayllanderson.gerenciadordecompras.core.services.utils;


import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsUtil {

    public static List<Product> getProductsNotPurchased(List<Product> allProducts){
        if (allProducts.isEmpty()) return Collections.emptyList();
        return allProducts.stream().filter(product -> !product.getPurchased()).collect(Collectors.toList());
    }

    public static List<Product> getProductsPurchased(List<Product> allProducts){
        if (allProducts.isEmpty()) return Collections.emptyList();
        return allProducts.stream().filter(Product::getPurchased).collect(Collectors.toList());
    }

    public static boolean isCompleted(List<Product> allProducts){
        return StatisticsUtil.getProductsNotPurchased(allProducts).isEmpty();
    }

    public static Integer getNumberOfProducts (List<Product> products){
        return products.size();
    }

    public static Integer getNumberOfProductsPurchased(List<Product> products){
        return StatisticsUtil.getProductsPurchased(products).size();
    }

    public static Integer getNumberOfProductsNotPurchased(List<Product> products){
        return StatisticsUtil.getProductsNotPurchased(products).size();
    }

    public static BigDecimal getTotalStipulated(List<Product> products){
        if (products.isEmpty()) return null;
        return products.stream().map(Product::getStipulatedPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getCurrentAmountTotal(List<Product> products){
        if (products.isEmpty()) return null;
        BigDecimal sum = new BigDecimal("0.0");
        for (Product product: products) {
            if (product.getPurchased()){
                sum = sum.add(product.getSpentPrice());
            } else {
                sum = sum.add((product.getStipulatedPrice()));
            }
        }
        return sum;
    }

    public static BigDecimal getCurrentAmountToSpent(List<Product> products){
        if (products.isEmpty()) return null;
        return products.stream().map(Product::getSpentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getCurrentAmountStipulated(List<Product> products){
        List<Product> productsNotPurchased = StatisticsUtil.getProductsNotPurchased(products);
        if (productsNotPurchased.isEmpty()) return null;
        return productsNotPurchased.stream()
                .map(product -> product.getStipulatedPrice().subtract(product.getSpentPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public static BigDecimal getAmountSaved(List<Product> products){
        List<Product> productsPurchased = StatisticsUtil.getProductsPurchased(products);
        if (productsPurchased.isEmpty()) return null;
       return productsPurchased.stream()
               .map(product -> product.getStipulatedPrice().subtract(product.getSpentPrice()))
               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getAvailableToSpend(Category category, BigDecimal currentAmountSpent){
        if (category.getBudget() == null) return null;
        return category.getBudget().subtract(currentAmountSpent);
    }

    public static BigDecimal getAvailableToSpendIfBuyAll(BigDecimal availableToSpend, BigDecimal currentAmountStipulated){
        if (availableToSpend == null || currentAmountStipulated == null) return null;
        return availableToSpend.subtract(currentAmountStipulated);
    }

    public static BigDecimal getCategoryBudget(Category category){
        if (category.getBudget() == null) return null;
        return category.getBudget();
    }

}
