package model.service;

import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Categoria;
import model.entities.Product;
import model.exception.ProductoException;
import model.util.FormatarTabela;

public class ProductService {

    private ProductDao dao;

    public ProductService(Categoria cat) {
	this.dao = DaoFactory.createProductDao(cat);
    }

    public boolean inserir(Product p) {
	try {
	    dao.inserir(p);
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }

    public void listarPordutos() {
	List<Product> list = dao.findAll();
	if (list.isEmpty()) {
	    throw new ProductoException("Ops, parece que você não tem nenhum produto na lista.");
	}
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0 ; i< list.size(); i++) {
	    FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i+1));
	}
	
    }

}
