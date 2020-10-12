package com.ray.model.service;

import com.ray.db.DbException;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.ProdutoException;

public class ProductServiceConsole extends ProductService{
    
    public ProductServiceConsole(Categoria cat) {
	super(cat);
    }


    public boolean deletar(Product p) {
   	try {
   	    if (dao.productIsValid(p.getId())) {
   		dao.deletById(p.getId());
   		cat.deletarProduto(p);
   		return true;
   	    } else {
   		throw new ProdutoException("Ocorreu um inesperado");
   	    }

   	} catch (DbException e) {
   	    e.printStackTrace();
   	    return false;
   	}
       }

       public void editarNome(Product p, String nome) {
   	p.setNome(nome);
   	dao.update(p);
       }

       public void editarPrecoEstipulado(Product p, double value) {
   	p.setPrecoEstipulado(value);
   	dao.update(p);
       }

       public void editarPrecoReal(Product p, double value) {
   	p.setPrecoReal(value);
   	dao.update(p);
       }

       public void marcarComoConcluido(Product p, double value) {
   	p.setPrecoReal(value);
   	if (!(p.getId() == null)) {
   	    p.setComprado(true);
   	    dao.update(p);
   	} else {
   	    p.setComprado(true);
   	}
       }

       public void marcarComoNaoConcluido(Product p, double value) {
   	p.setPrecoReal(value);
   	if (!(p.getId() == null)) {
   	    p.setComprado(false);
   	    dao.update(p);
   	} else {
   	    p.setPrecoReal(value);
   	}
       }

       public void mudarCategoria(Product p, Categoria cat) throws ProdutoException {
   	p.setCategoria(cat);
   	dao.update(p);
       }
    
}