package com.ray.model.dao;

import java.util.List;

import com.ray.model.entities.Categoria;

public interface CategoriaDao {
    
    Categoria save(Categoria categoria);  
    void deletById(Long id);  
    void update(Categoria categoria);
    Categoria findById(Long id);
    /**
     * @return a list that contains the all Category of an User
     */
    List <Categoria> findAll();
    List<Categoria> findByName(String name);
}
