package model.dao;

import java.util.List;

import model.entities.Categoria;
import model.entities.User;

public interface CategoriaDao {
    
    void inserir(Categoria categoria);  
    void deletById(Integer id);  
    void atualizar(Categoria categoria);
    Categoria findById(Integer id);
    Categoria findByName(String name);
    List <Categoria> findAll();
    List <Categoria> findAll(User user);
}
