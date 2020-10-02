package com.ray.model.dao;

import java.util.List;

import com.ray.model.entities.Categoria;

public interface CategoriaDao {
    
    void inserir(Categoria categoria);  
    void deletById(Integer id);  
    void atualizar(Categoria categoria);
    Categoria findById(Integer id);
    /**
     * @return a list that contains the all Category of an User
     */
    List <Categoria> findAll();
    List<Categoria> findByName(String name);
}
