package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.CategoriaDao;
import model.entities.Categoria;

public class CategoriaJDBC implements CategoriaDao{
    
    private Connection conn;

    public CategoriaJDBC(Connection conn) {
	this.conn = conn;
    }

    @Override
    public void inserir(Categoria categoria) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void deletById(Integer id) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void atualizar(Categoria categoria) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public Categoria findById(Integer id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Categoria> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Categoria findByName(String name) {
	// TODO Auto-generated method stub
	return null;
    }
    
}
