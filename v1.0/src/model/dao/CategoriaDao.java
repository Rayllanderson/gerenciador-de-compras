package model.dao;

import java.util.List;

import model.entities.Categoria;

public interface CategoriaDao {

    void inserir(Categoria categoria);

    void deletById(Integer id);

    void atualizar(Categoria categoria);

    /**
     * @return a list that contains the all Category of an User
     */
    List<Categoria> findAll();
}
