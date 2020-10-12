package com.ray.model.service;

import java.util.List;

import com.ray.db.DbException;
import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.ProductDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.EntradaInvalidaException;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.exception.ProductoException;

public class ProductService {
    
    public ProductDao dao;
    public Categoria cat;

    public ProductService(Categoria cat) {
	this.cat = cat;
	this.dao = DaoFactory.createProductDao(cat);
    }

    public Categoria getCat() {
	return cat;
    }

    // ---------------------"CRUD"-------------------------//
    /**
     * @param p
     * @return
     * @throws EntradaInvalidaException - caso os campos valor estipalo e nome
     *                                  estejam vazios ou nulos
     */
    public boolean inserir(Product p) throws EntradaInvalidaException {
	try {
	    validarCampos(p);
	    dao.save(p);
	    cat.adicionarProduto(p);
	    return true;
	} catch (DbException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    /**
     * 
     * @param p
     * @return
     * @throws EntradaInvalidaException - caso os campos valor estipalo e nome
     *                                  estejam vazios ou nulos
     */
    public boolean update(Product p) throws EntradaInvalidaException {
	try {
	    System.out.println(p.getId());
	    if (dao.validar(p.getId())) {
		validarCampos(p);
		dao.update(p);
		return true;
	    } else {
		throw new ProductoException("Ocorreu um inesperado");
	    }
	} catch (DbException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public boolean deleteById(Long id) {
   	try {
   	    dao.deletById(id);
   	    return true;
   	} catch (DbException e) {
   	    e.printStackTrace();
   	    return false;
   	}
       }
    
    /**
     * 
     * @return
     */
    public List<Product> findAll() {
	return dao.findAll();
    }
    
    public List<Product> findAllWithException() throws ListaVaziaException {
	List<Product> list = dao.findAll();
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Ops, parece que você não tem nenhum produto na lista.");
	}
	return list;
    }
    

    public List<Product> searchProductByName(String name) throws ListaVaziaException {
	List<Product> list = dao.findByName(name);
	if (list.isEmpty()) {
	    throw new ListaVaziaException(
		    !(name.length() == 1) ? "Não existe nenhum produto com as letras '" + name + "'"
			    : "Não existe nenhum produto com a letra '" + name + "'");
	}
	return list;
    }
    
    
    // ---------------------------Listas-------------------------------//
    /**
     * verifica se o usuario tem protudos. 
     * 
     * @return true caso esteja vazia <br>
     *         false contenha um ou mais produtos
     */
    public boolean listIsEmpty() {
	return dao.findAll().isEmpty() ? true : false;
    }
    
   
    
    // --------------------------- validações -----------------------------------//
    
    /**
     * Valida os campos Nome e preço estipulado
     * 
     * @param p
     * @throws EntradaInvalidaException lançada quando os campos nome e preço
     *                                  estipulado estiverem em branco ou nulos
     */
    private void validarCampos(Product p) throws EntradaInvalidaException {
	if (p.getNome().trim().isEmpty() || p.getPrecoEstipulado() == null) {
	    throw new EntradaInvalidaException("um ou mais campos estão vazios.");
	}
    }

    
    // ----------------------------úteis---------------------------------------//


}
