package com.ray.model.util;

import java.util.ArrayList;
import java.util.List;

import com.ray.model.entities.Categoria;
import com.ray.model.entities.Product;
import com.ray.model.entities.User;
import com.ray.model.exception.ListaVaziaException;
import com.ray.model.service.CategoriaService;
import com.ray.model.service.ProductService;

public class CalculoTotal {
    private CategoriaService cService;

    public CalculoTotal(User user) {
	this.cService = new CategoriaService(user);
    }

    private void instanciarTodosProdutos(Categoria cat) {
	ProductService pService = new ProductService(cat);
	List<Product> list = pService.findAll();
	for (Product p : list) {
	    cat.adicionarProduto(p);
	}
    }

    /**
     * @return uma lista contendo todos os produtos de um usuário
     */
    private List<Product> getTodosProdutos() {
	List<Categoria> listCategoria = cService.findAll();
	for (int i = 0; i < listCategoria.size(); i++) {
	    instanciarTodosProdutos(listCategoria.get(i));
	}
	List<Product> list = new ArrayList<>();
	for (int i = 0; i < listCategoria.size(); i++) {
	    list.addAll(listCategoria.get(i).getProductList());
	}
	return list;
    }

    public int getNumProdutos() {
	return getTodosProdutos().size();
    }
    
    /**
     * 
     * @return total do valor que acha que vai pagar de todas as listas
     */
    public double getEstipulado() {
	double total = 0;
	for (Product p : getTodosProdutos()) {
	    total += p.getPrecoEstipulado();
	}
	return total;
    }
    
    /**
     * @return quanto já gastou de todas as listas
     */
    public double getValorGasto() {
	double total = 0;
	for (Product p : this.getTodosProdutos()) {
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
	for (Product p : getTodosProdutos()) {
	    if(p.isComprado()) {
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
	for (Product p : getTodosProdutos()) {
	    if(!p.isComprado()) {
		total += p.getPrecoEstipulado();
	    }
	}
	return total;
    }
    
    /**
     * 
     * @return valor total de todos os produtos das listas. se o produto for comprado, leva em consideração o valor real
     */
    public double getTotal() {
	double total = 0;
	for (Product p : getTodosProdutos()) {
	    if(p.isComprado()) {
		total += p.getPrecoReal();
	    }else {
		total += p.getPrecoEstipulado();
	    }
	}
	return total;
    }
    
    public int getNumProdutosComprados() {
	int total = 0;
	for (Product p : this.getTodosProdutos()) {
	    if (p.isComprado()) {
		total++;
	    }
	}
	return total;
    }

    /**
     * @return quanto já gastou de todas as listas
     * ps: apenas os produtos comprados
     */
    public double getGastoComprados() {
	double total = 0;
	for (Product p : this.getTodosProdutos()) {
	    if(p.isComprado()) {
		total += p.getPrecoReal();
	    }
	}
	return total;
    }

    /**
     * 
     * @return total do valor que acha que vai pagar de todas as listas, porém só os que ja foram comprados
     */
    public double getEstipuladoComprados() {
	double total = 0;
	for (Product p : getTodosProdutos()) {
	    if(p.isComprado()) {
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
