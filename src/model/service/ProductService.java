package model.service;

import java.util.List;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Categoria;
import model.entities.Product;
import model.exception.ListaVaziaException;
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
	    throw new ListaVaziaException("Ops, parece que você não tem nenhum produto na lista.");
	}
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	}
    }

    public Product getProdutoByNumer(int num) {
	List<Product> list = dao.findAll();
	if (list.isEmpty()) {
	    throw new ListaVaziaException("Ops, parece que você não tem nenhum produto na lista.");
	}
	if (num > list.size()) {
	    throw new ProductoException(
		    "Parece não existe nenhum produto com número " + num + ". Verifique a tabela e tente novamente");
	}
	return list.get(num - 1);
    }

    public boolean atualizar(Product p) {
	try {
	    dao.atualizar(p);
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }

    public boolean deletar(Product p) {
	try {
	    dao.deletById(p.getId());
	    return true;
	} catch (DbException e) {
	    return false;
	}
    }

    public void editarNome(Product p, String nome) {
	p.setNome(nome);
	dao.atualizar(p);
    }

    public void editarPrecoEstipulado(Product p, double value) {
	p.setPrecoEstipulado(value);
	dao.atualizar(p);
    }

    public void editarPrecoReal(Product p, double value) {
	p.setPrecoReal(value);
	dao.atualizar(p);
    }

    public void marcarComoConcluido(Product p, double value) {
	if (p.isCompraro()) {
	    throw new ProductoException("Produto já estava marcado como comprado");
	}
	p.setPrecoReal(value);
	if (!(p.getId() == null)) {
	    p.setCompraro(true);
	    dao.atualizar(p);
	} else {
	    p.setCompraro(true);
	}
    }

    public void marcarComoNaoConcluido(Product p, double value) {
	if (!(p.isCompraro())) {
	    throw new ProductoException("Produto já estava marcado como não comprado");
	}
	p.setPrecoReal(value);
	if (!(p.getId() == null)) {
	    p.setCompraro(false);
	    dao.atualizar(p);
	} else {
	    p.setPrecoReal(value);
	}
    }

    public void listarNaoConcluidos() {
	List<Product> list = dao.findAll();
	if (list.isEmpty()) {
	    throw new ProductoException("Ops, parece que você não tem nenhum produto na lista.");
	}
	int maxLenName = FormatarTabela.maxLenghtName(list) + 2;
	FormatarTabela.printInvoiceHeader(maxLenName + 2, 5);
	for (int i = 0; i < list.size(); i++) {
	    if (!(list.get(i).isCompraro())) {
		FormatarTabela.printInvoice(list.get(i), maxLenName + 2, 5, (i + 1));
	    }

	}
    }

    // talvez editar categoria futuramente...
}
