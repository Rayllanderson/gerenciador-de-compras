package com.ray.model.service;

import java.util.List;

import com.ray.db.DbException;
import com.ray.model.dao.CategoriaDao;
import com.ray.model.dao.DaoFactory;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.User;
import com.ray.model.exception.CategoriaInexistenteException;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.validacoes.Validacao;

public class CategoriaService {

    public CategoriaService(User user) {
	this.categoriaDao = DaoFactory.createCategoriaDao(user);
    }

    protected CategoriaDao categoriaDao;

    // --------------------------- CRUD ----------------------------------
    /**
     * 
     * @param cat
     * @return a categoria salva no banco de dados, com id
     * @throws EntradaInvalidaException - caso o nome da categoria esteja nulo
     */
    public Categoria save(Categoria cat) throws EntradaInvalidaException {
	try {
	   if (cat.getOrcamento() == null) {
		cat.setOrcamento(0.0);
	    }
	   Validacao.validarNome(cat.getName());
	   return categoriaDao.save(cat);
	} catch (DbException e) {
	    return null;
	}
    }
   
    public List<Categoria> findAll() {
	return categoriaDao.findAll();
    }
    
    public boolean deleteById(Long id) {
	try {
	    categoriaDao.deletById(id);
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }

    /**
     * @param cat
     * @return true caso dê tudo ok
     * @throws CategoriaInexistenteException - caso a categoria editada não exista (questão de segurança apenas, para não permitir mudar a categoria editando o html, por exemplo)
     * @throws EntradaInvalidaException - caso o nome da categoria esteja nulo
     */
    public boolean update(Categoria cat) throws CategoriaInexistenteException, EntradaInvalidaException {
	try {
	    if (cat.getOrcamento() == null) {
		cat.setOrcamento(0.0);
	    }
	    Validacao.validarCategoria(cat);
	    Validacao.validarNome(cat.getName());
	    categoriaDao.update(cat);
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }
    
    // -------------------------------- outros -------------------------------//
    
    public List<Categoria> findCategoriaByName(String name) throws ListaVaziaException {
	List<Categoria> list = categoriaDao.findByName(name);
	if (list.isEmpty()) {
	    throw new ListaVaziaException(!(name.length() == 1) ? "Não existe nenhuma lista com as letras '" + name +"'": "Não existe nenhuma lista com a letra '" + name + "'");
	}
	return list;
    }

}
