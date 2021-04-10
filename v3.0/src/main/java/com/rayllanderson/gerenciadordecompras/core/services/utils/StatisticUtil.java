package com.rayllanderson.gerenciadordecompras.core.services.utils;


import com.rayllanderson.gerenciadordecompras.core.model.Category;
import com.rayllanderson.gerenciadordecompras.core.model.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticUtil {

    public static List<Product> getProductsNotPurchased(List<Product> allProducts){
        if (allProducts.isEmpty()) return Collections.emptyList();
        return allProducts.stream().filter(product -> !product.getPurchased()).collect(Collectors.toList());
    }

    public static List<Product> getProductsPurchased(List<Product> allProducts){
        if (allProducts.isEmpty()) return Collections.emptyList();
        return allProducts.stream().filter(Product::getPurchased).collect(Collectors.toList());
    }

    public static boolean isCompleted(List<Product> allProducts){
        return StatisticUtil.getProductsNotPurchased(allProducts).isEmpty();
    }

    public static Integer getNumberOfProducts (List<Product> products){
        return products.size();
    }

    public static Integer getNumberOfProductsPurchased(List<Product> products){
        return StatisticUtil.getProductsPurchased(products).size();
    }

    public static Integer getNumberOfProductsNotPurchased(List<Product> products){
        return StatisticUtil.getProductsNotPurchased(products).size();
    }

    public static BigDecimal getTotalStipulated(List<Product> products){
        if (products.isEmpty()) return BigDecimal.ZERO;
        return products.stream().map(Product::getStipulatedPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getCurrentAmountTotal(List<Product> products){
        if (products.isEmpty()) return BigDecimal.ZERO;
        BigDecimal sum = BigDecimal.ZERO;
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
        if (products.isEmpty()) return BigDecimal.ZERO;
        return products.stream().map(Product::getSpentPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getCurrentAmountStipulated(List<Product> products){
        List<Product> productsNotPurchased = StatisticUtil.getProductsNotPurchased(products);
        if (productsNotPurchased.isEmpty()) return BigDecimal.ZERO;
        return productsNotPurchased.stream()
                .map(product -> product.getStipulatedPrice().subtract(product.getSpentPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public static BigDecimal getAmountSaved(List<Product> products){
        List<Product> productsPurchased = StatisticUtil.getProductsPurchased(products);
        if (productsPurchased.isEmpty()) return BigDecimal.ZERO;
       return productsPurchased.stream()
               .map(product -> product.getStipulatedPrice().subtract(product.getSpentPrice()))
               .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static BigDecimal getAvailableToSpend(Category category, BigDecimal currentAmountSpent){
        if (category.getBudget() == null) return BigDecimal.ZERO;
        return category.getBudget().subtract(currentAmountSpent);
    }

    public static BigDecimal getAvailableToSpendIfBuyAll(BigDecimal availableToSpend, BigDecimal currentAmountStipulated){
        if (availableToSpend == null || currentAmountStipulated == null) return BigDecimal.ZERO;
        return availableToSpend.subtract(currentAmountStipulated);
    }

    public static BigDecimal getCategoryBudget(Category category){
        if (category.getBudget() == null) return BigDecimal.ZERO;
        return category.getBudget();
    }

}
