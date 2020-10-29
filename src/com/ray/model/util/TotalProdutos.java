package com.ray.model.util;

import java.util.ArrayList;
import java.util.List;

import com.ray.model.dao.DaoFactory;
import com.ray.model.dao.ProductDao;
import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.entities.User;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.CategoriaService;
import com.ray.model.service.ProductService;

public class TotalProdutos {

    private CategoriaService cService;
    private User user = null;
    private ProductDao dao = DaoFactory.createProductDao(null);
   
    public TotalProdutos(User user) {
	this.cService = new CategoriaService(user);
	this.user = user;
    }

//    private void instanciarTodosProdutos(Categoria cat) {
//	ProductService pService = new ProductService(cat);
//	List<Product> list = pService.findAll();
//	for (Product p : list) {
//	    cat.adicionarProduto(p);
//	}
//    }

    /**
     * @return uma lista contendo todos os produtos de um usuário
     */
    private List<Product> getAll() {
	return dao.findAll(this.user.getId());
    }

    public int getNumProdutos() {
	return getAll().size();
    }

    /**
     * 
     * @return total do valor que acha que vai pagar de todas as listas
     */
    public double getEstipulado() {
	double total = 0;
	for (Product p : getAll()) {
	    total += p.getPrecoEstipulado();
	}
	return total;
    }

    /**
     * @return quanto já gastou de todas as listas
     */
    public double getValorGasto() {
	double total = 0;
	for (Product p : this.getAll()) {
	    total += p.getPrecoReal();
	}
	return total;
    }

    /**
     * 
     * @return total do valor que economizou
     */
    public double getEconomizado() {
	double total = 0;
	for (Product p : getAll()) {
	    if (p.isComprado()) {
		total += (p.getPrecoEstipulado() - p.getPrecoReal());
	    }
	}
	return total;
    }

    /**
     * 
     * @return total do valor que acha que vai pagar de todas as listas
     */
    public double getRestante() {
	double total = 0;
	for (Product p : getAll()) {
	    if (!p.isComprado()) {
		total += p.getPrecoEstipulado();
	    }
	}
	return total;
    }

    /**
     * 
     * @return valor total de todos os produtos das listas. se o produto for
     *         comprado, leva em consideração o valor real
     */
    public double getTotal() {
	double total = 0;
	for (Product p : getAll()) {
	    if (p.isComprado()) {
		total += p.getPrecoReal();
	    } else {
		total += p.getPrecoEstipulado();
	    }
	}
	return total;
    }

    public int getNumProdutosComprados() {
	int total = 0;
	for (Product p : this.getAll()) {
	    if (p.isComprado()) {
		total++;
	    }
	}
	return total;
    }

    /**
     * @return quanto já gastou de todas as listas ps: apenas os produtos comprados
     */
    public double getGastoComprados() {
	double total = 0;
	for (Product p : this.getAll()) {
	    if (p.isComprado()) {
		total += p.getPrecoReal();
	    }
	}
	return total;
    }

    /**
     * 
     * @return total do valor que acha que vai pagar de todas as listas, porém só os
     *         que ja foram comprados
     */
    public double getEstipuladoComprados() {
	double total = 0;
	for (Product p : getAll()) {
	    if (p.isComprado()) {
		total += p.getPrecoEstipulado();
	    }
	}
	return total;
    }

    /**
     * @return list.size();
     * @throws ListaVaziaException("Você não possui listas no momento");
     */
    public int getNumCategorias() throws ListaVaziaException {
	List<Categoria> list = this.cService.findAll();
	return list.size();
    }
}
