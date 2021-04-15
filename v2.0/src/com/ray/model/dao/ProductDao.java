package com.ray.model.dao;

import java.util.List;

import com.ray.model.entities.Product;

public interface ProductDao {

    void save(Product obj);

    void update(Product obj);

    void deletById(Long id);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findByName(String name);

    /**
     * Verifica no banco de dados se o produto pertence de fato ao usu�rio atual
     *
     * @param id do produto
     *
     * @return true se o produto pertencer ao usuario, false se nao
     */
    boolean productIsValid(Long id);
}
