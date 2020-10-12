package com.ray.model.service;

import java.util.List;

import com.ray.db.DbException;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.exception.ProductoException;
import com.ray.model.util.FormatarTabela;

public class ProductServiceConsole extends ProductService{
    
    public ProductServiceConsole(Categoria cat) {
	super(cat);
    }

    /**
     * @throws ListaVaziaException("Ops, parece que você não tem nenhum produto na
     *                                   lista.");
     */
    public void listarPordutosConsole() throws ListaVaziaException {
	List<Product> list = dao.findAll();
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Ops, parece que você não tem nenhum produto na lista.");
	}
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 6);
	for (int i = 0; i < list.size(); i++) {
	    FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	}
    }

    public void listarNaoConcluidosConsole() throws ListaVaziaException {
	List<Product> list = getNaoConcluidos();
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    if (!(list.get(i).isComprado())) {
		FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	    }
	}
    }

    public void listarConcluidosConsole() throws ListaVaziaException {
	List<Product> list = getConcluidos();
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    if (list.get(i).isComprado()) {
		FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	    }
	}
    }
    
    public boolean deletar(Product p) {
   	try {
   	    if (dao.validar(p.getId())) {
   		dao.deletById(p.getId());
   		cat.deletarProduto(p);
   		return true;
   	    } else {
   		throw new ProductoException("Ocorreu um inesperado");
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

       public void mudarCategoria(Product p, Categoria cat) throws ProductoException {
   	p.setCategoria(cat);
   	dao.update(p);
       }
    
}