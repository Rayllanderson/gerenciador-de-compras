package model.dao;

import java.util.List;

import model.entities.Product;

public interface ProductDao {

    void inserir(Product obj);
    void atualizar(Product obj);
    void deletById(Integer id);
    Product findById(Integer id);
    List <Product> findAll();
    
}
